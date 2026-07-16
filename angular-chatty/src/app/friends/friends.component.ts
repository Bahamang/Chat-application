import { Component, OnInit } from '@angular/core';
import { Service } from '../service/service';
import { FriendsService } from '../service/friends-service';
import { Router } from '@angular/router';
import { Profile } from '../model/profile';
import { MediaService } from '../service/media-service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss', '../app.component.scss']
})
export class FriendsComponent implements OnInit {

  constructor(private service: Service, private router: Router,
  private friendsService: FriendsService, private mediaService: MediaService) { }

  profileId = 1; // Example profile ID, replace with actual dynamic ID as needed
  users : Profile[] = []
  viewingFriends = true; // True when showing friends, false for others


  ngOnInit(): void {
    this.loadFriends(); // Default view
  }

  loadFriends(): void {
    this.viewingFriends = true;
    this.friendsService.getMyFriends(this.profileId).subscribe( (data:any) => {
      this.users = data;
    });
  }

  loadOtherUsers(): void {
    this.viewingFriends = false;
    this.friendsService.getOtherUsers(this.profileId).subscribe((data:any)=> {
      this.users = data;
    });
  }

  addUser(userId: number): void {
    this.friendsService.addUser(this.profileId, userId).subscribe(() => {
      // Optionally refresh the list after adding
      if (!this.viewingFriends) {
        this.loadOtherUsers();
      }
    });
  }

  removeUser(userId: number): void {
    this.friendsService.removeUser(this.profileId, userId).subscribe(() => {
      // Refresh the list after removal
      if (this.viewingFriends) {
        this.loadFriends();
      }
    });
  }

  loadImage(data : any): any{
      if(data && data.content){
        return this.mediaService.getSafeImageURL(data?.content);
      }
  }

}
