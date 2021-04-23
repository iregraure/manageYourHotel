import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from 'src/app/interfaces/clientInterface';
import { ClientsManagementService } from '../../services/clients-management.service';

@Component({
  selector: 'app-clients-management',
  templateUrl: './clients-management.component.html',
  styleUrls: ['./clients-management.component.scss']
})
export class ClientsManagementComponent implements OnInit {

  searchForm: FormGroup;

  clientsList: Client[];

  tableCols: ['name', 'surname', 'dni'];

  constructor(private clientsManagementService: ClientsManagementService,
              private router: Router) { }

  ngOnInit(): void 
  {
    this.searchForm = new FormGroup(
      {
        search: new FormControl('',[])
      }
    )

    this.clientsList = [{name: 'name', surname: 'surname', dni: 'dni', email: 'email', address: 'address', phone: 'phone', birthDate: 'birthDate'},
                        {name: 'name', surname: 'surname', dni: 'dni', email: 'email', address: 'address', phone: 'phone', birthDate: 'birthDate'},
                        {name: 'name', surname: 'surname', dni: 'dni', email: 'email', address: 'address', phone: 'phone', birthDate: 'birthDate'},
                        {name: 'name', surname: 'surname', dni: 'dni', email: 'email', address: 'address', phone: 'phone', birthDate: 'birthDate'},
                        {name: 'name', surname: 'surname', dni: 'dni', email: 'email', address: 'address', phone: 'phone', birthDate: 'birthDate'}];
    // this.clientsManagementService.getAllClients().subscribe(clients =>
    //   {
    //     this.clientsList = clients;
    //     console.log(this.clientsList)
    //   })
  }

  newClient()
  {
    this.router.navigate(['newClient']);
  }

}
