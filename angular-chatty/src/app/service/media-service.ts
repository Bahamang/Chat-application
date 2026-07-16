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
import { Media } from '../model/media'

@Injectable({
  providedIn: 'root'
})
export class MediaService{

  private baseUrl = 'http://localhost:8080/api/media';

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }


  getById(id: number): Observable<Media> {
    return this.http.get<Media>(`${this.baseUrl}/${id}`);
  }

  create(entity: Media): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, entity);
  }

  update(id: number, entity: Media): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, entity);
  }

  getAll(): Observable<Media[]> {
    return this.http.get<Media[]>(`${this.baseUrl}/getAll`);
  }



  decodeBase64(plainText: string): string {
    // Decode plain text to Uint8Array
    const uint8Array = new Uint8Array(plainText.length);
    for (let i = 0; i < plainText.length; i++) {
      uint8Array[i] = plainText.charCodeAt(i);
    }

    // Convert Uint8Array to array of numbers (number[])
    const byteArray = Array.from(uint8Array);

    // Convert array of numbers to Base64
    return atob(String.fromCharCode.apply(null, byteArray));
  }

  encodeToBase64(plainText: string): string {
    // Decode plain text to Uint8Array
    const uint8Array = new Uint8Array(plainText.length);
    for (let i = 0; i < plainText.length; i++) {
      uint8Array[i] = plainText.charCodeAt(i);
    }

    // Convert Uint8Array to array of numbers (number[])
    const byteArray = Array.from(uint8Array);

    // Convert array of numbers to Base64
    return btoa(String.fromCharCode.apply(null, byteArray));
  }

  getSafeImageURL(base64Image: string): SafeUrl {
    const imageBytes = this.decodeBase64(base64Image);
    const imageUrl = `${imageBytes}`;
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }

}
