import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private router: Router) { }

   login(username: string, password: string): boolean {
    // Store the credentials upon successful login. In a real application, you'd verify credentials via an API.
    sessionStorage.setItem('username', username);
    sessionStorage.setItem('password', password);
    sessionStorage.removeItem('wrongPassword');
    var isLogged :any = false;
    this.tryLogin().subscribe(
      (data : any) => {
         isLogged = true;
         this.router.navigate(['/']); // Navigate to the home page or dashboard

          setTimeout(() => window.location.reload(), 10);
       },
       (error: any) => {
         console.error('Error fetching:', error);
       }
    );
    return isLogged;

  }

  logout(): void {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('password');
    sessionStorage.removeItem('wrongPassword');
    // Optionally, invalidate the session on the backend as well
    this.router.navigate(['/login']); // Redirect to the login page
  }

  tryLogin(): Observable<any> {
    return this.http.get<void>(`${this.baseUrl}/dummy`);
  }

}
