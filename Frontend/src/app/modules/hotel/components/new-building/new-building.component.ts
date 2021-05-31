import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Building } from 'src/app/interfaces/buildingInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { RoomManagementService } from '../../services/room-management.service';

@Component({
  selector: 'app-new-building',
  templateUrl: './new-building.component.html',
  styleUrls: ['./new-building.component.scss']
})
export class NewBuildingComponent implements OnInit {

  building: Building;

  constructor(private router: Router,
              private roomManagementService: RoomManagementService,
              private alerts: AlertsService) { }

  ngOnInit(): void 
  {
    this.building = 
    {
      name: '',
      address: ''
    }
  }

  create()
  {
    this.roomManagementService.createBuilding(this.building).subscribe(res =>
      {
        this.alerts.showSnackbar("Building created");
        this.router.navigate(['hotel', 'rooms']);
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  cancel()
  {
    this.router.navigate(['hotel', 'rooms']);
  }

}
