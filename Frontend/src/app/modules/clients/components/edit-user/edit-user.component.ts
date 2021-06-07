import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/userInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { JwtManagerService } from 'src/app/services/jwt-manager.service';
import { UserManagementService } from '../../services/user-management.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {
  
  user: User;

  username: string;

  constructor(private userService: UserManagementService,
              private alerts: AlertsService,
              private location: Location,
              private jwt: JwtManagerService) { }

  ngOnInit(): void 
  {
    this.getUser();
  }

  getUser()
  {
    let jwt = this.jwt.getJwt();
    let decoded = jwt_decode(jwt);
    this.username = decoded['username'];
    this.userService.getUser(this.username).subscribe(res =>
      {
        this.user = res;
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  update()
  {
    this.userService.updateUser(this.user, this.username).subscribe(res =>
      {
        this.username = this.user.username;
        this.location.back();
        this.alerts.showSnackbar("User updated");
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  back()
  {
    this.location.back();
  }

}
