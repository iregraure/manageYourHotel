import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/interfaces/clientInterface';
import { User } from 'src/app/interfaces/userInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { JwtManagerService } from 'src/app/services/jwt-manager.service';
import { UserManagementService } from '../../services/user-management.service';
import jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.scss']
})
export class PersonalDataComponent implements OnInit {

  user: User;

  client: Client;

  constructor(private userService: UserManagementService,
              private alerts: AlertsService,
              private location: Location,
              private jwt: JwtManagerService,
              private router: Router) { }

  ngOnInit(): void 
  {
    this.getClient();
  }

  getClient()
  {
    let jwt = this.jwt.getJwt();
    let decoded = jwt_decode(jwt);
    let username = decoded['username'];
    this.userService.getClient(username).subscribe(res =>
      {
        this.client = res;
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  updateUser()
  {
    this.router.navigate(['clients', 'user']);
  }

  update()
  {
    this.userService.updateClient(this.client).subscribe(res =>
      {
        this.alerts.showSnackbar("Personal data updated");
      })
  }

  back()
  {
    this.location.back();
  }

}
