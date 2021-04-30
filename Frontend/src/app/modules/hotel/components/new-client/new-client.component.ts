import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/interfaces/clientInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { ClientsManagementService } from '../../services/clients-management.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.scss']
})
export class NewClientComponent implements OnInit {

  client: Client;

  constructor(private router: Router,
              private clientsManagementService: ClientsManagementService,
              private alerts: AlertsService) { }

  ngOnInit(): void 
  {
    this.client = 
    {
      name: '',
      surname: '',
      dni: '',
      email: '',
      address: '',
      phone: '',
      birthDate: ''
    }
  }

  create()
  {
    this.clientsManagementService.createClient(this.client).subscribe(res =>
      {
        this.alerts.showSnackbar("Client created");
        this.router.navigate(['/hotel/clients']);
      },
      error =>
      {
        this.alerts.errorDialog("Something went bad");
      })
  }

  cancel()
  {
    this.router.navigate(['/hotel/clients']);
  }

}
