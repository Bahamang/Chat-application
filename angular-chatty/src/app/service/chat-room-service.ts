import { Post } from '../model/post';
import { Chat } from '../model/chat';
import { Message } from '../model/message';
import { User } from '../model/users';
import { Profile } from '../model/profile';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChatRoomService{

  private baseUrl = 'http://localhost:8080/api/chatrooms';

  constructor(private http: HttpClient) { }

  getById(chatRoomId: number, myProfileId: number): Observable<Chat> {
    return this.http.get<Chat>(`${this.baseUrl}/myChat/${chatRoomId}`);
  }

  create(chatRoomEntity: Chat): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, chatRoomEntity);
  }

  update(chatRoomId: number, chatRoomEntity: Chat): Observable<any> {
    return this.http.put(`${this.baseUrl}/${chatRoomId}`, chatRoomEntity);
  }

  getAll(): Observable<Chat[]> {
    return this.http.get<Chat[]>(`${this.baseUrl}/getAll`);
  }

  getMyChatRooms(firstUserId: number): Observable<Chat[]> {
    return this.http.get<Chat[]>(`${this.baseUrl}/all-my-chats`);
  }

  startChat(): Observable<Profile[]> {
    return this.http.get<Profile[]>(`${this.baseUrl}/start-chat`);
  }

  createNewChat(secondUserId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/create-new-chat/${secondUserId}`);
  }

}
