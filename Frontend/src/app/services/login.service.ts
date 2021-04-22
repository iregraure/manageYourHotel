import { HttpClient } from '@angular/common/http';
import { getUrlScheme } from '@angular/compiler';
import { Injectable, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
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

  @Output() userChanges = new EventEmitter<User>();

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

  getLoggedUser(): Observable<User>
  {
    return this.http.get<User>('http://localhost:8080/user/' + this.user.username).pipe(tap(getUser =>
      {
        if((this.user == null && getUser != null) ||
          (this.user != null && getUser == null) ||
          (this.user != null && getUser != null && this.user.username != getUser.username))
          {
            this.emitUserChange();
            this.user = getUser;
          }
      }));
  }

  emitUserChange()
  {
    this.getLoggedUser().subscribe(loggedUser => 
      {
        this.userChanges.emit(loggedUser);
      });
  }

}
