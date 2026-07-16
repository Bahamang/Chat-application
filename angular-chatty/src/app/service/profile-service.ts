import { Post } from '../model/post';
import { Chat } from '../model/chat';
import { Message } from '../model/message';
import { User } from '../model/users';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { catchError } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService{

  private baseUrl = 'http://localhost:8080/api/profiles';

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }


    // Get profile by ID
    getProfileById(profileId: number): Observable<any> {
      return this.http.get<any>(`${this.baseUrl}/${profileId}`)
        .pipe(
          catchError(error => {
            throw new Error(`Failed to get profile by ID ${profileId}: ${error.message}`);
          })
        );
    }

    myProfile :any;
    // Get profile by ID
    getMyProfile(): Observable<any> {
      return this.http.get<any>(`${this.baseUrl}/get-my-profile`)
        .pipe(
          catchError(error => {
            throw new Error(`Failed to get profile by ID  ${error.message}`);
          })
        );
    }


    // Create profile
    createProfile(profileEntity: any): Observable<any> {
      return this.http.post<any>(`${this.baseUrl}/create`, profileEntity);
    }

    // Update profile by ID
    updateProfileById(profileId: number, profileEntity: any): Observable<any> {
      return this.http.put<any>(`${this.baseUrl}/${profileId}`, profileEntity);
    }

    // Get all profiles
    getAllProfiles(): Observable<any[]> {
      return this.http.get<any[]>(`${this.baseUrl}/getAll`)
        .pipe(
          catchError(error => {
            throw new Error(`Failed to get all profiles: ${error.message}`);
          })
        );
    }


  private refreshDataSubject = new Subject<any>();

  public refreshDataObservable = this.refreshDataSubject.asObservable();

  public triggerRefreshData() {
    this.refreshDataSubject.next();
  }

}
