import { HttpClient } from '@angular/common/http';
import { Injectable, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/userInterface';
import { JwtManagerService } from './jwt-manager.service';

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

  public isUserLogged = new Subject<boolean>();
  public isLogged = this.isUserLogged.asObservable();
  public url = '';

  constructor(private http: HttpClient,
              private jwt: JwtManagerService) { }

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

  isLoggedIn(url: string): boolean
  {
    const logged = this.jwt.getJwt();
    if(!logged)
    {
      this.url = url;
      return false;
    }
    return true;
  }

}
