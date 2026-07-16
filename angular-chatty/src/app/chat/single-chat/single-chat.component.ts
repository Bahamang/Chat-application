import { Component, OnInit, Input, ElementRef, AfterViewInit, ViewChild, NgZone  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Message } from '../../model/message';
import { Service } from '../../service/service';
import { Profile } from '../../model/profile';
import { MessageService } from '../../service/message-service';
import { MediaService } from '../../service/media-service';
import { User } from '../../model/users';
import { Media } from '../../model/media';
import { ChatRoomService } from '../../service/chat-room-service';
import { ProfileService } from '../../service/profile-service';

@Component({
  selector: 'app-single-chat',
  templateUrl: './single-chat.component.html',
  styleUrls: ['./single-chat.component.scss', '../../app.component.scss']
})
export class SingleChatComponent implements OnInit {

  @ViewChild('chatContainer') chatContainer!: ElementRef;

  myProfile: any;
  id: number = 1;
  loaded : any = false;
  chat : any;

  chat_with: string | undefined = '';
  current_user_id : string = "1";
  your_message : string = "";
  user: any;
  messages : Message[] = [];

  constructor(private route: ActivatedRoute, private service: Service, private messageService: MessageService,
   private mediaService: MediaService, private chatRoomService: ChatRoomService, private profileService: ProfileService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
        this.id = params['id'];
      });
    this.profileService.getMyProfile().subscribe(
      (data : any) => {
         this.myProfile = data;
       },
       (error: any) => {
         console.error('Error fetching media:', error);
       }
    );

    this.chatRoomService.getById(this.id, this.myProfile).subscribe(
         (data : any) => {
           this.chat = data;
         },
         (error: any) => {
           console.error('Error fetching media:', error);
         }
    );

    this.messageService.getByChatId(this.id).subscribe(
         (data : any) => {
           this.messages = data;

           setTimeout(() => this.scrollToBottom(), 1);
           this.loaded = true;
         },
         (error: any) => {
           console.error('Error fetching media:', error);
         }
    );

  }

  loadImage(media: Media): any   {
    if (this.messages && media) {
      return this.mediaService.getSafeImageURL(media.content);
    }
  }

  returnBackground(media:Media) : any{
    if (this.messages && media) {
      return `url("${ this.loadImage(media).changingThisBreaksApplicationSecurity}")`;
    }
  }

  sendMessage(){
    if(this.messages){
      let copyObject = Object.assign({}, this.messages[0]); // This creates a shallow copy
      copyObject.text = this.your_message;
      copyObject.senderId = this.myProfile.id;
      copyObject.sentToId = this.chat.profile.id;
      copyObject.chatId = this.chat.id;
      copyObject.senderProfile = this.myProfile;

      this.messageService.create(copyObject).subscribe(
           (data : any) => {
              this.your_message = "";
              this.messages.push(copyObject);
              setTimeout(() => this.scrollToBottom(), 1);
           },
           (error: any) => {
             console.error('Error fetching media:', error);
           }
      );
    }
  }

  ngAfterViewInit() {
  }

  private scrollToBottom(): void {
    try {
      this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
    } catch(err) { }
  }

}
