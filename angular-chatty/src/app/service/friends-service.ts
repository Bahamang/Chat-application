import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {
  private baseUrl = 'http://localhost:8080/api/friends'; // Adjust if your API base URL is different

  constructor(private http: HttpClient) { }

  getMyFriends(profileId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/added`, null);
  }

  getOtherUsers(profileId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/other`, null);
  }

  addUser(profileId: number, userIdToAdd: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/add/${userIdToAdd}`, null);
  }

  removeUser(profileId: number, userIdToRemove: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/remove/${userIdToRemove}`, null);
  }

}
