import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NavigationComponent } from './navigation/navigation.component';
import { ProfileComponent } from './profile/profile.component';
import { MainComponent } from './main/main.component';
import { ChatComponent } from './chat/chat.component';
import { LoginComponent } from './login/login.component';
import { FriendsComponent } from './friends/friends.component';
import { SingleChatComponent } from './chat/single-chat/single-chat.component'


const routes: Routes = [
     { path: 'profile', component:  ProfileComponent},
     { path: 'main', component:  MainComponent},
     { path: 'chat', component: ChatComponent },
     { path: 'chat/:id', component: SingleChatComponent},
     { path: 'friends', component: FriendsComponent },
     { path: 'login', component: LoginComponent },
     { path: '',   redirectTo: '/main', pathMatch: 'full' },
     { path: '**', component: MainComponent }
   ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {



 }
