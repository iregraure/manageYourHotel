import { Component, OnInit} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Client } from 'src/app/interfaces/clientInterface';
import { ClientsManagementService } from '../../services/clients-management.service';

@Component({
  selector: 'app-clients-management',
  templateUrl: './clients-management.component.html',
  styleUrls: ['./clients-management.component.scss'],
})
export class ClientsManagementComponent implements OnInit {
  searchForm: FormGroup;

  clientsList: Client[] = [];

  sortedList: Client[];

  dataSource: MatTableDataSource<Client> = new MatTableDataSource(this.clientsList);

  tableCols: string[] = ['name', 'surname', 'dni', 'details'];

  page: number;

  constructor(
    private clientsManagementService: ClientsManagementService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchForm = new FormGroup(
    {
      search: new FormControl('', []),
    });

    this.clientsManagementService.getAllClients().subscribe((clients) => 
    {
      this.clientsList = clients;
      this.sortedList = this.clientsList.slice();
      this.dataSource = new MatTableDataSource(this.sortedList);
    });

    this.page = 1;
  }

  applyFilter(event: Event)
  {
    let filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim();
  }

  sortData(sort: Sort)
  {
    let data = this.clientsList.slice();
    if(!sort.active || sort.direction === '')
    {
      this.sortedList = data;
      this.dataSource = new MatTableDataSource(data);
    }
    else
    {
      this.sortedList = data.sort((a, b) => 
      {
        let ascendent = (sort.direction === 'asc');
        switch(sort.active)
        {
          case 'name': return this.compare(a.name, b.name, ascendent);
          case 'surname': return this.compare(a.surname, b.surname, ascendent);
          case 'dni': return this.compare(a.dni, b.dni, ascendent);
          default: return 0;
        }
      });
    }
  }

  compare(a: string, b: string, ascendent: boolean): number
  {
    return ((a < b) ? -1 : 1) * (ascendent ? 1 : -1);
  }

  newClient() {
    this.router.navigate(['hotel', 'newClient']);
  }

  showDetails(dni: string)
  {
    this.router.navigate([`hotel/client/${dni}`]);
  }
}
