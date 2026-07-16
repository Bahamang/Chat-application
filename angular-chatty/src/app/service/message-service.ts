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
export class MessageService{

 private baseUrl = 'http://localhost:8080/api/messages';

  constructor(private http: HttpClient) { }

  getOneMessageById(messageId: number): Observable<Message> {
    return this.http.get<Message>(`${this.baseUrl}/get-message/${messageId}`);
  }

  getByChatId(messageId: number): Observable<Message> {
    return this.http.get<Message>(`${this.baseUrl}/get-by-chat-id/${messageId}`);
  }

  create(messageEntity: Message): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, messageEntity);
  }

  update(messageId: number, messageEntity: Message): Observable<any> {
    return this.http.put(`${this.baseUrl}/${messageId}`, messageEntity);
  }

  getAll(): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.baseUrl}/getAll`);
  }


}
