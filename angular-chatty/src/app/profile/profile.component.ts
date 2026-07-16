import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Service } from '../service/service';
import { ProfileService } from '../service/profile-service';
import { UserService } from '../service/user-service';
import { MediaService } from '../service/media-service';
import { User } from '../model/users';
import { Profile } from '../model/profile';
import { Media } from '../model/media';
import {MatSnackBar} from '@angular/material/snack-bar';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { AuthService } from '../service/auth-service';

import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss', '../app.component.scss']
})
export class ProfileComponent implements OnInit {

  profileId : any;

  profile: Profile | null = null;
  media: Media | null = null;

  nickname = '';
  user : any;
  oldPassword = '';

  avatar = '';

  image:  any;
  medias: any;

  passwordMatching = true;
  oldPassMatching = true;

  dialogRef: any;

  pass = {pass1:'', pass2:'', oldPass:''};


  oldPassVisible: boolean = false;
  pass1Visible: boolean = false;
  pass2Visible: boolean = false;

  @ViewChild('passDialog') passDialog = {} as TemplateRef<Password>;

  constructor(private service: Service, public dialog: MatDialog, private profileService: ProfileService,
   private mediaService: MediaService,private _snackBar: MatSnackBar, private sanitizer: DomSanitizer,
    private authService: AuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.profileId = this.service.whoAmI();

    this.user = this.service.getUserById("1");

    this.nickname = this.user!.nickname;

    this.profileService.getMyProfile().subscribe(
          (data: any) => {
            this.profile = data;
            this.loadImage(this.profile!.photo);
          },
          (error: any) => {
            console.error('Error fetching media:', error);
          }
    );

  }

  loadImage(data : any){
      if(data){
        this.media = data;
        this.image = this.mediaService.getSafeImageURL(data?.content);
      }
  }


  selectedFile: File | undefined;

  imageBase64String: string | undefined;

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

  onSubmit(): void {
    if (!this.selectedFile) {
      return; // No file selected
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = () => {
      this.imageBase64String = reader.result as string;
      this.image = this.imageBase64String;
      console.log('Image base64 string:', this.imageBase64String);
    };
  }

  updateProfile(){
    this.profileService.updateProfileById(this.profile!.id, this.profile).subscribe((data:any) => {
      this.refreshProfile();
    });
  }

  changePicture(): void{
      console.log(this.media);
      if(this.media){
        this.media.content = this.mediaService.encodeToBase64(this.image);
        this.mediaService.update(this.media.id, this.media).subscribe((data:any) => {
           this.refreshProfile();
         });
      }
  }


  openInfoDialog() {
    this.pass = {pass1:'', pass2:'', oldPass:''};
    this.passwordMatching = true;
    this.oldPassMatching = true;
    this.dialogRef = this.dialog.open(this.passDialog,
      { data: this.pass, height: '50vh', width: '40vw' });

    this.dialogRef.afterClosed().subscribe((result: Password) => {
      if(result){
        console.log('The Info dialog was closed.');
        console.log(result!.pass1, result!.pass2);
      }
    });
  }

 onCancelDialog() {
    this.dialogRef.close();
  }

  acceptDialog(password: Password) {
    this.passwordMatching = true;
    this.oldPassMatching = true;
    if(password.pass1 != password.pass2){
      this.passwordMatching = false;
    }
    else{

      this.userService.checkPassword(password.oldPass).subscribe(isCorrect => {
        if(isCorrect){
          console.log(password.pass1);
          this.userService.changePassword(password.pass1).subscribe(() => {
            this.dialogRef.close(password);
            this.openSnackBar();
            this.logout();
          }, error => {
            console.error('Error changing password', error);
          });
        }
        else{
          this.oldPassMatching = false;
        }
      }, error => {
        console.error('Error checking password', error);
      });

    }
  }

  openSnackBar() {
    this._snackBar.open("Your password was changed", "ok");
  }

  logout(): void {
    this.authService.logout();
  }

  refreshProfile() {
   window.location.reload();
  }

}

export interface Password {
    oldPass : string;
    pass1: string;
    pass2: string;
}
