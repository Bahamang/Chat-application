import { Post } from '../model/post';
import { Chat } from '../model/chat';
import { Message } from '../model/message';
import { User } from '../model/users';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class Service{


  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

   users: User[] = [
      { id:"1", nickname: 'Baikhan', password:'123456', fullName: 'Baikhan', email: 'john.doe@example.com', age: 30, country: 'USA', avatarUrl: 'https://avatars.mds.yandex.net/i?id=ea6de5611284a59a32b46b54ba8712a5f4d8f1b0-5899861-images-thumbs&n=13' },
      { id:"2", nickname: 'AliceSmith', password:'123456', fullName: 'Alice Smith', email: 'alice.smith@example.com', age: 25, country: 'Canada', avatarUrl: 'https://material.angular.io/assets/img/examples/shiba2.jpg' },
      { id:"3", nickname: 'BobJohnson', password:'123456', fullName: 'Bob Johnson', email: 'bob.johnson@example.com', age: 35, country: 'UK', avatarUrl: 'https://avatars.mds.yandex.net/i?id=f99cc4c30f248d033b5f89453ffd6ff1ffe3e092-10544851-images-thumbs&n=13' },
      { id:"4", nickname: 'EmmaBrown', password:'123456', fullName: 'Emma Brown', email: 'emma.brown@example.com', age: 28, country: 'Australia', avatarUrl: 'https://avatars.mds.yandex.net/get-entity_search/5578182/808870021/SUx182' },
      { id:"5", nickname: 'MichaelTaylor', password:'123456', fullName: 'Michael Taylor', email: 'michael.taylor@example.com', age: 32, country: 'Germany', avatarUrl: 'https://avatars.mds.yandex.net/get-entity_search/922086/850847282/SUx182' },
      { id:"6", nickname: 'Clown', password:'123456', fullName: 'Clown', email: 'Clown.taylor@example.com', age: 32, country: 'Germany', avatarUrl: 'https://avatars.mds.yandex.net/get-entity_search/767653/726649697/SUx182_2x' },
    ];


  getMessages(chatIdToFind: string): Message[] | undefined{
      return undefined;
  }

  getChatById(passedId: string): Chat | undefined {
      return undefined;
  }

  sendMessage(message: Message){
  }

  updateChat(passedId: string, message: Message){
  }

  getUsers(): User[] {
      return this.users;
    }

  getUserById(passedId: string): User | undefined {
    const filteredUsers = this.getUsers().filter(user => user.id === passedId);
    return filteredUsers.length > 0 ? filteredUsers[0] : undefined;
  }

  updateProfile(passedId: string, name: string) : void{
      this.getUserById(passedId)!.nickname = name;
  }

  changePicture(passedId: string, avatar: string) : void{
      this.getUserById(passedId)!.avatarUrl = avatar;
  }


  //  ---------------------------------------------------------------------------------------------

    whoAmI(){
      return 1;
    }


}
