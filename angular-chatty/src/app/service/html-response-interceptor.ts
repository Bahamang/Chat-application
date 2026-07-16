import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class HtmlResponseInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    return next.handle(req).pipe(tap(event => {
      if (event instanceof HttpResponse) {
        // Check if the content type is HTML, indicating a redirection to the login page
        const contentType = event.headers.get('Content-Type');
        if (contentType && contentType.includes('text/html')) {
          this.router.navigate(['http://localhost:8080/login']); // Redirect to your Angular login route
        }
      }
    }, error => {
      // Handle HTTP errors
    }));
  }
}
