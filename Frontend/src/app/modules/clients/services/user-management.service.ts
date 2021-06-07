import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/interfaces/clientInterface';
import { Stay } from 'src/app/interfaces/stayInterface';
import { User } from 'src/app/interfaces/userInterface';

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor(private http: HttpClient) { }

  getClient(username: string): Observable<Client>
  {
    return this.http.get<Client>(`http://localhost:8080/client/user/${username}`);
  }

  updateClient(client: Client)
  {
    return this.http.put<Client>(`http://localhost:8080/client/${client.dni}`, client);
  }

  getUser(username: string): Observable<User>
  {
    return this.http.get<User>(`http://localhost:8080/user/${username}`);
  }

  updateUser(user: User, username: string)
  {
    return this.http.put<User>(`http://localhost:8080/user/${username}`, user);
  }

  getAllStays(dni: string): Observable<Stay[]>
  {
    return this.http.get<Stay[]>(`http://localhost:8080/client/${dni}/stays`);
  }

  getStay(dni: string, startDate: string): Observable<Stay>
  {
    return this.http.get<Stay>(`http://localhost:8080/client/${dni}/stays/${startDate}`);
  }
}
