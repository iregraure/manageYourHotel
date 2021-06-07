import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { Stay } from 'src/app/interfaces/stayInterface';
import { JwtManagerService } from 'src/app/services/jwt-manager.service';
import { UserManagementService } from '../../services/user-management.service';
import jwt_decode from 'jwt-decode';
import { AlertsService } from 'src/app/services/alerts.service';

@Component({
  selector: 'app-stays-list',
  templateUrl: './stays-list.component.html',
  styleUrls: ['./stays-list.component.scss']
})
export class StaysListComponent implements OnInit {

  staysList: Stay[] = [];

  constructor(
    private userService: UserManagementService,
    private router: Router,
    private jwt: JwtManagerService,
    private alerts: AlertsService
  ) { }

  ngOnInit(): void 
  {
    this.getStays();
  }

  getStays()
  {
    let jwt = this.jwt.getJwt();
    let decoded = jwt_decode(jwt);
    let username = decoded['username'];
    this.userService.getAllStays(username).subscribe(res =>
      {
        this.staysList = res;
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  showDetails(startDate: string)
  {
    this.router.navigate([`clients/stay/${startDate}`]);
  }

}
