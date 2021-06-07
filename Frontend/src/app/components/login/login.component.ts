import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertsService } from 'src/app/services/alerts.service';
import { JwtManagerService } from 'src/app/services/jwt-manager.service';
import { LoginService } from 'src/app/services/login.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide = true;
  
  constructor(private router: Router,
              private jwtManager: JwtManagerService,
              private alerts: AlertsService,
              private loginService: LoginService) { }

  ngOnInit(): void 
  {
    this.loginForm = new FormGroup(
      {
        username: new FormControl('12345678R', [Validators.required]),
        password: new FormControl('12345678R', [Validators.required])
      }
    )
  }

  login()
  {
    // Show the loading spinner while we validate the login
    this.alerts.loadingDialog();
    // Subscribe to the http petition
    this.loginService.validateUser(this.loginForm.controls.username.value, this.loginForm.controls.password.value).subscribe((res) =>
    {
      // If we receive a token, we save it and navigate to the correct page
      if(res != null && res != undefined)
      {
        this.jwtManager.saveJwt(res);
        let decoded = jwt_decode(res);
        let roles = decoded['roles'];
        this.alerts.closeDialog();
        this.loginService.emitUserChange();
        if(roles[0] == "CLIENT")
        {
          this.router.navigate(["/clients"]);
        }
        else
        {
          this.router.navigate(["/hotel"]);
        }
      }
    },
    (error) =>
    {
      this.alerts.closeDialog();
      if(error.status == 401)
      {
        this.alerts.errorDialog("Incorrect credentials");
      }
      else
      {
        this.alerts.errorDialog("Unexpected error");
      }
    })
  }
}
