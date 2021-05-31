import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Building } from 'src/app/interfaces/buildingInterface';
import { Floor } from 'src/app/interfaces/floorInterface';
import { Room } from 'src/app/interfaces/roomInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { RoomManagementService } from '../../services/room-management.service';

@Component({
  selector: 'app-new-room',
  templateUrl: './new-room.component.html',
  styleUrls: ['./new-room.component.scss']
})
export class NewRoomComponent implements OnInit {

  roomList: Room[] = [];

  buildingList: Building[] = [];

  floorList: Floor[] = [];

  room: Room;

  copyRoom: number;

  building: string;

  floor: number;

  smoker: boolean;

  tv: boolean;

  airConditioning: boolean;

  breakfast: boolean;

  constructor(private router: Router,
              private roomManagementService: RoomManagementService,
              private alerts: AlertsService) { }

  ngOnInit(): void 
  {
    this.room = 
    {
      number: 0,
      type: '',
      price: 0.0,
      smoker: false,
      tv: false,
      airConditioning: false,
      breakfast: false,
      floorNumber: 0,
      buildingName: '',
      state: ''
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

  getRooms()
  {
    this.roomManagementService.getAllRoomsInBuilding(this.room.buildingName).subscribe(res =>
      {
        this.roomList = res;
      },
      error =>
      {
        if(error.status == 404)
        {
          this.alerts.infoDialog("There are no rooms. You can't copy from any room");
        }
        else
        {
          this.alerts.errorDialog(error.error);
        }
      })
  }

  getFloors()
  {
    this.roomManagementService.getAllFloorsInBuilding(this.room.buildingName).subscribe(res =>
      {
        this.floorList = res;
      },
      error =>
      {
        if(error.status == 404)
        {
          this.alerts.infoDialog("There are no floors in this building");
        }
        else
        {
          this.alerts.errorDialog(error.error);
        }
      })
  }

  buildingChange(building: string)
  {
    this.building = building;
    this.room.buildingName = building;
    this.getRooms();
    this.getFloors();
  }

  create()
  {
    this.room.floorNumber = this.floor;
    this.room.smoker = this.smoker;
    this.room.tv = this.tv;
    this.room.airConditioning = this.airConditioning;
    this.room.breakfast = this.breakfast;
    this.room.state = "Free";
    if(this.room.buildingName == '')
    {
      this.alerts.infoDialog("You have to select a building");
    }
    else if(this.room.floorNumber == 0)
    {
      this.alerts.infoDialog("You have to select a floor");
    }
    else
    {
      this.roomManagementService.createRoom(this.room).subscribe(res =>
        {
          this.alerts.showSnackbar("Room created");
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
