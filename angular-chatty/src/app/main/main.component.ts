import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Service } from '../service/service';
import { PostService } from '../service/post-service';
import { MediaService } from '../service/media-service';
import { Post } from '../model/post';
import { Media } from '../model/media';
import { finalize } from 'rxjs/operators';
import { ProfileService } from '../service/profile-service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss', '../app.component.scss']
})
export class MainComponent implements OnInit {

  @ViewChild('createPostTemplate') createPostTemplate!: TemplateRef<any>;


  myProfile: any;
  posts: Post[] = [];
  loaded : any = false;
  postText: string = ''; // Variable to hold the textarea input

  private refreshSub: any;

  selectedFile: File | undefined;
  imageBase64String: string | undefined;
  image:  any;

  constructor(private service: Service, private profileService: ProfileService,
  private mediaService: MediaService, private postService: PostService,private dialog: MatDialog){
  }


  toggleImageSize() {
    this.isImageEnlarged = !this.isImageEnlarged;
  }

  isImageEnlarged = false;
  enlargedImageSrc = '';

  openEnlargedImage(imgSrc: string) {
    this.enlargedImageSrc = imgSrc;
    this.isImageEnlarged = true;
  }

  closeEnlargedImage() {
    this.isImageEnlarged = false;
  }

  ngOnInit(): void {
    this.refreshSub = this.profileService.refreshDataObservable.subscribe((data:any) => {
        this.refreshData();
      });
    this.profileService.getMyProfile().subscribe(
           (data: any) => {
             this.myProfile = data.id;
           },
           (error: any) => {
             console.error('Error fetching media:', error);
           }
     );
     this.postService.getAll().subscribe(
           (data : any) => {
             this.posts = data;
             this.loaded = true;
           }
     );
  }

  refreshData(){
    this.profileService.getMyProfile().subscribe(
           (data: any) => {
             this.myProfile = data.id;
           },
           (error: any) => {
             console.error('Error fetching media:', error);
           }
     );
   }

  ngAfterViewInit(): void {

  }

  loadImage(media: Media): any   {
    if (this.posts && media) {
      return this.mediaService.getSafeImageURL(media.content);
    }
  }

  returnBackground(media:Media) : any{
    if (this.posts) {
      return `url("${ this.loadImage(media).changingThisBreaksApplicationSecurity}")`;
    }
  }

  openDialog(): void {
    this.dialog.open(this.createPostTemplate, {
      width: '500px',
    });
  }

  postMessage(): void {

    if(this.image){
        var newMedia: Media = {
          id: null,
          content: this.mediaService.encodeToBase64(this.image),
          type: "jpeg",
          created: new Date(),
          updated: new Date(),
          disabled: false,
          deleted: false,
        }
        this.mediaService.create(newMedia).subscribe(
          (data:any) => {
            var newPost: Post = this.getTemplatePost();
            newPost.text = this.postText;
            newPost.profileId = this.myProfile;
            newPost.mediaId = data;
            console.log(newPost);
            this.postService.create(newPost).subscribe((data:any) => {
               this.postService.getAll().subscribe(
                     (data : any) => {
                       this.posts = data;
                       this.loaded = true;
                       this.clear();
                     }
               );
            });
          }
        );
    }else{
        var newPost: Post = this.getTemplatePost();
        newPost.text = this.postText;
        newPost.profileId = this.myProfile;
        console.log(newPost);
        this.postService.create(newPost).subscribe((data:any) => {
           this.postService.getAll().subscribe(
                 (data : any) => {
                   this.posts = data;
                   this.loaded = true;
                   this.clear();
                 }
           );
        });
    }


  }


  clear(): void {
    this.postText = ''; // Optionally reset the textarea input after posting
    this.selectedFile = undefined;
    this.imageBase64String = undefined;
    this.image =null;
  }

  getTemplatePost(){
    var newPost: Post = {
        media: null,
        text: null,
        id: null, // or some relevant unique identifier if available
        profile: null, // Assuming this can be null or set it to a valid profile object
        profileId: null, // Set this if you have a specific profileId
        mediaId: null, // Assuming an ID if you're managing these
        created:  new Date(), // Current date-time
        updated: new Date(), // Can be same as created if just creating
        disabled: false, // Default to false or as per your logic
        deleted: false, // Default to false or as per your logic
      };
    return newPost;
  }

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


}
