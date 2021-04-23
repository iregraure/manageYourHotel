import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/interfaces/clientInterface';

@Injectable({
  providedIn: 'root'
})
export class ClientsManagementService {

  constructor(private http: HttpClient) { }

  getAllClients(): Observable<Client[]>
  {
    return this.http.get<Client[]>('http://localhost:8080/client/clients');
  }

  createClient(client: Client)
  {
    return this.http.post<Client>('http://localhost:8080/user/signUpClient', client);
  }
}
