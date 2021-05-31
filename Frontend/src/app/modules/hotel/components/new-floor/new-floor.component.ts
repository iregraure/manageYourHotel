import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertsComponent } from 'src/app/components/alerts/alerts.component';
import { Building } from 'src/app/interfaces/buildingInterface';
import { Floor } from 'src/app/interfaces/floorInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { RoomManagementService } from '../../services/room-management.service';
import { RoomManagementComponent } from '../room-management/room-management.component';

@Component({
  selector: 'app-new-floor',
  templateUrl: './new-floor.component.html',
  styleUrls: ['./new-floor.component.scss']
})
export class NewFloorComponent implements OnInit {

  floor: Floor;

  buildingList: Building[] = [];

  selectedOption: string;

  constructor(private router: Router,
              private roomManagementService: RoomManagementService,
              private alerts: AlertsService) { }

  ngOnInit(): void 
  {
    this.floor = 
    {
      buildingName: '',
      number: 0
    };

    this.getBuildings();
  }

  getBuildings()
  {
    this.roomManagementService.getAllBuildings().subscribe(res =>
      {
        this.buildingList = res;
      },
      error =>
      {
        if(error.status == 404)
        {
          this.alerts.infoDialog("There are no buildings. You have to create one first!");
        }
        else
        {
          this.alerts.errorDialog(error.error);
        }
      })
  }

  create()
  {
    this.floor.buildingName = this.selectedOption;
    if(this.floor.buildingName == '')
    {
      this.alerts.infoDialog("You have to select a building");
    }
    else
    {
      this.roomManagementService.createFloor(this.floor).subscribe(res =>
        {
          this.alerts.showSnackbar("Floor created");
          this.router.navigate(['hotel', 'rooms']);
        },
        error =>
        {
          this.alerts.errorDialog(error.error);
        })
    }
  }

  cancel()
  {
    this.router.navigate(['hotel', 'rooms']);
  }

}
