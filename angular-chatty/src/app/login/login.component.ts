import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  hide = true; // Used for toggling password visibility
  loginError = false;
  loginErrorMessage = 'Incorrect username or password.';

  ngOnInit(): void {
    console.log("trying login");
  }
  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    var error = !this.authService.login(this.username, this.password);

     setTimeout(() => this.loginError=error, 10);
  }

}
