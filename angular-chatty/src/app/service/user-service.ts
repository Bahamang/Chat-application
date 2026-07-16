import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/getAll`);
  }

  getById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${userId}`);
  }

  createUser(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, user);
  }

  disableUser(userId: number, user: User): Observable<any> {
    return this.http.put(`${this.baseUrl}/${userId}/disable`, user);
  }

  changePassword(password: string): Observable<void> {
    const url = `${this.baseUrl}/change-password`;
    return this.http.post<void>(url, password, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  checkPassword(password: string): Observable<boolean> {
    const url = `${this.baseUrl}/password-check`;
    return this.http.post<boolean>(url, password, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

}
