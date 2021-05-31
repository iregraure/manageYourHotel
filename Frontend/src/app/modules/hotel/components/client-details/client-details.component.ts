import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/interfaces/clientInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { ClientsManagementService } from '../../services/clients-management.service';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.scss']
})
export class ClientDetailsComponent implements OnInit {

  client: Client;

  constructor(private activeRoute: ActivatedRoute,
              private clientManagementService: ClientsManagementService,
              private router: Router,
              private alerts: AlertsService) { }

  ngOnInit(): void 
  {
    this.getClient();
  }

  getClient()
  {
    let dni = this.activeRoute.snapshot.paramMap.get('dni');
    this.clientManagementService.getClient(dni).subscribe(client =>
      {
        this.client = client;
      })
  }

  update()
  {
    this.clientManagementService.updateClient(this.client).subscribe(client =>
      {
        this.router.navigate(['hotel', 'clients']);
        this.alerts.showSnackbar("Client updated");
      })
  }

  back()
  {
    this.router.navigate(['hotel', 'clients']);
  }

}
