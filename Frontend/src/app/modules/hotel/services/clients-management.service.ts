import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/interfaces/clientInterface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientsManagementService {

  constructor(private http: HttpClient) { }

  getAllClients(): Observable<Client[]>
  {
    return this.http.get<Client[]>(environment.getClientsUrl);
  }

  createClient(client: Client)
  {
    return this.http.post<Client>(environment.createClientUrl, client);
  }

  getClient(dni: string): Observable<Client>
  {
    return this.http.get<Client>(`/client/${dni}`);
  }

  updateClient(client: Client)
  {
    return this.http.put<Client>(`/client/${client.dni}`, client);
  }
}
