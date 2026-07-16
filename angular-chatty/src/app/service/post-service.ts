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
export class PostService{

  private baseUrl = 'http://localhost:8080/api/posts';

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getById(postId: number): Observable<Post> {
    return this.http.get<Post>(`${this.baseUrl}/${postId}`);
  }

  create(postEntity: Post): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, postEntity);
  }

  update(postId: number, postEntity: Post): Observable<any> {
    return this.http.put(`${this.baseUrl}/${postId}`, postEntity);
  }

  getAll(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.baseUrl}/getAll`);
  }

}
