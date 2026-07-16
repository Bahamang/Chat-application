import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpResponse,
    HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private router: Router) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // Attempt to retrieve credentials from session storage
        const username = sessionStorage.getItem('username');
        const password = sessionStorage.getItem('password');

        if (username && password) {
            const basicAuthHeader = 'Basic ' + window.btoa(username + ':' + password);
            request = request.clone({
                setHeaders: {
                    Authorization: basicAuthHeader
                }
            });
        }

        return next.handle(request).pipe(
             catchError((err: HttpErrorResponse) => {
                 if (err.status === 401 || err.status === 403) {
                     console.log("unauthorized");
                     // Handle unauthorized responses by navigating to a login or error page
                     if(this.router.url != '/login')
                        this.router.navigate(['/login']);
                 }
                 // For other HTTP errors, simply rethrow the error
                 return throwError(err);
             })
         );
    }




}
