import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth-service';
import { Router } from '@angular/router';
import { ProfileService } from '../service/profile-service';
import { MediaService } from '../service/media-service';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  constructor(private authService: AuthService, private profileService: ProfileService,
   private mediaService: MediaService, private router: Router) { }

  myProfile: any;

  ngOnInit(): void {
    this.profileService.getMyProfile().subscribe(
           (data: any) => {
             this.myProfile = data;
             console.log(data);
           },
           (error: any) => {
             console.error('Error fetching media:', error);
           }
     );
  }

  logout(): void {
    this.authService.logout();
  }
  get isLoginPage(): boolean {
    return this.router.url === '/login';
  }

  loadImage(media: any): any   {
    if (this.myProfile && media) {
      return this.mediaService.getSafeImageURL(media.content);
    }
  }

  returnBackground(media:any) : any{
    if (this.myProfile && media) {
      return `url("${ this.loadImage(media).changingThisBreaksApplicationSecurity}")`;
    }
  }

}
