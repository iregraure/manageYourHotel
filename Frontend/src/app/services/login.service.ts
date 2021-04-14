import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/userInterface';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  user: User = 
  {
    username: "",
    password: ""
  };

  constructor(private http: HttpClient) { }

  validateUser(username: string, password: string)
  {
    this.user = 
    {
      username: username,
      password: password
    }

    return this.http.post(environment.serverUrl + environment.loginUrl, this.user, { responseType: 'text' });
  }

}
