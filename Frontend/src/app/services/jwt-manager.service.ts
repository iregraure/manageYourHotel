// Service to manage jwt in local storage
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtManagerService {

  constructor() { }

  saveJwt(token: string)
  {
    localStorage.setItem("jwt", token);
  }

  getJwt(): string
  {
    return localStorage.getItem("jwt");
  }

  removeJwt()
  {
    localStorage.removeItem("jwt");
  }

}
