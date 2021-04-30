import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Types } from 'src/app/interfaces/alerts-type';
import { User } from 'src/app/interfaces/userInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { JwtManagerService } from 'src/app/services/jwt-manager.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: User; // To save the logged user

  constructor(private loginService: LoginService,
              private router: Router,
              private jwtManager: JwtManagerService,
              private alerts: AlertsService) { }

  ngOnInit(): void 
  {
    this.loginService.userChanges.subscribe(newUser =>
      {
        this.user = newUser;
      });
  }

  goHome()
  {
    this.router.navigate(["/hotel"]);
  }

  logOut()
  {
    this.alerts.confirmDialog("Dou you really want to log out?").subscribe(op =>
      {
        if(op == Types.ACCEPT)
          {
            this.jwtManager.removeJwt();
            this.router.navigate(["/login"]);
          }
      })
  }

}
