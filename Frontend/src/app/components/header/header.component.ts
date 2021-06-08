import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Types } from 'src/app/interfaces/alerts-type';
import { User } from 'src/app/interfaces/userInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { JwtManagerService } from 'src/app/services/jwt-manager.service';
import { LoginService } from 'src/app/services/login.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: User; // To save the logged user

  logged: boolean;

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

    // this.logged = this.loginService.isLoggedIn('');
    // this.loginService.userChanges.subscribe(logged =>
    //   {
    //     this.logged = logged;
    //   })
  }

  goHome()
  {
    let jwt = this.jwtManager.getJwt();
    let decoded = jwt_decode(jwt);
    let rol = decoded['roles'];
    if(rol == 'CLIENT')
    {
      this.router.navigate(['/clients']);
    }
    else if(rol == 'RECEPTION')
    {
      this.router.navigate(['/hotel']);
    }
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
