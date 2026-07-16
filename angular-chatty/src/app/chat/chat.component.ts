import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog'; // Include MatDialogRef here
import { Service } from '../service/service';
import { Chat } from '../model/chat';
import { Router } from '@angular/router';
import { Media } from '../model/media';
import { PostService } from '../service/post-service';
import { MediaService } from '../service/media-service';
import { ChatRoomService } from '../service/chat-room-service';
import { Profile } from '../model/profile';
import { ProfileService } from '../service/profile-service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss', '../app.component.scss']
})
export class ChatComponent implements OnInit {

  @ViewChild('selectProfileTemplate') selectProfileTemplate!: TemplateRef<any>;
  profiles: Profile[] = [];
  private dialogRef!: MatDialogRef<any>; // Add a property to hold the dialog reference

  chats: any = [];
  myProfile: any;
  loaded : any = false;

  current_user_id : string = "1";

  friendsChats: any = [];
  nonFriendsChats: any = [];

  constructor(private service: Service, private router: Router, private chatRoomService: ChatRoomService, private profileService: ProfileService,
              private dialog: MatDialog, private mediaService: MediaService, private postService: PostService){
  }

  ngOnInit(): void {

    this.profileService.getMyProfile().subscribe(
      (data : any) => {
         this.myProfile = data;

        this.chatRoomService.getMyChatRooms(this.myProfile.id).subscribe(
             (data : any) => {
               this.chats = data;
               this.loaded = true;
               this.organizeChats();
             },
             (error: any) => {
               console.error('Error fetching media:', error);
             }
        );
       },
       (error: any) => {
         console.error('Error fetching media:', error);
       }
    );

  }


  organizeChats() {
    if(this.myProfile.friendsId){
      const friendsIdsArray = this.myProfile.friendsId.split(',').map((id:any) => parseInt(id, 10));
      this.chats.forEach((chat:any) => {
        if (friendsIdsArray.includes(chat.firstUsersId) || friendsIdsArray.includes(chat.secondUsersId)) {
          this.friendsChats.push(chat);
        } else {
          this.nonFriendsChats.push(chat);
        }
      });
    }
    else{
      this.nonFriendsChats = this.chats;
    }
  }

  goToChat(id: number){
    this.router.navigateByUrl('/chat/'+ id);
  }


  loadImage(media: Media): any   {
    if (media) {
      return this.mediaService.getSafeImageURL(media.content);
    }
  }

  returnBackground(media:Media) : any{
    if (this.chats) {
      return `url("${ this.loadImage(media).changingThisBreaksApplicationSecurity}")`;
    }
  }



  closeDialog(): void {
    this.dialogRef.close();
  }

  openProfileDialog(): void {
    this.chatRoomService.startChat().subscribe(profiles => {
        console.log(profiles);
      this.profiles = profiles;
      const dialogRef = this.dialog.open(this.selectProfileTemplate, { width: '300px' });

      dialogRef.afterClosed().subscribe( (result:any) => {
        console.log('Dialog was closed');
        // Perform actions after dialog is closed if necessary
      });
    });
  }

  selectProfile(profile: Profile, dialogRef: MatDialogRef<any>): void {
    // Here you can implement the selection logic and close the dialog
    console.log(`Selected profile: ${profile.username}`);
    this.chatRoomService.createNewChat(profile.userId).subscribe( (chatRoom:any) => {
      console.log(chatRoom); // Handle the newly created chat room

      this.chatRoomService.getMyChatRooms(this.myProfile.id).subscribe(
           (data : any) => {
             console.log(data);
             this.chats = data;
             this.loaded = true;
           },
           (error: any) => {
             console.error('Error fetching media:', error);
           }
      );
    });
  }


}
