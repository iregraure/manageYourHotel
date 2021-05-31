import { state } from '@angular/animations';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Room } from 'src/app/interfaces/roomInterface';
import { RoomState } from 'src/app/interfaces/roomStateInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { RoomManagementService } from '../../services/room-management.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss']
})
export class UpdateRoomComponent implements OnInit {

  room: Room;

  buildingName: string;

  smoker: boolean;

  tv: boolean;

  airConditioning: boolean;

  breakfast: boolean;

  statesList: RoomState[];

  constructor(private activeRoute: ActivatedRoute,
              private roomManagementService: RoomManagementService,
              private router: Router,
              private alerts: AlertsService,
              private location: Location) { }

  ngOnInit(): void 
  {
    this.buildingName = this.roomManagementService.getBuildingName();

    this.getStates();

  }
  
  getRoom()
  {
    let number = this.activeRoute.snapshot.paramMap.get('number');
    let roomNumber = Number(number);
    this.roomManagementService.getRoom(this.buildingName, roomNumber).subscribe(room =>
      {
        this.room = room;
        this.smoker = this.room.smoker;
        this.tv = this.room.tv;
        this.airConditioning = this.room.airConditioning;
        this.breakfast = this.room.breakfast;
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  getStates()
  {
    this.roomManagementService.getAllRoomStates().subscribe(states =>
      {
        this.statesList = states;
        this.getRoom();
      })
  }

  update()
  {
    this.room.smoker = this.smoker;
    this.room.tv = this.tv;
    this.room.airConditioning = this.airConditioning;
    this.room.breakfast = this.breakfast;
    this.roomManagementService.updateRoom(this.buildingName, this.room).subscribe(res =>
      {
        let state: RoomState = 
        {
          state: this.room.state
        }
        this.roomManagementService.changeState(this.buildingName, this.room.number, state).subscribe(resul => 
          {
            this.alerts.showSnackbar("Room updated");
            this.location.back();
          },
          error =>
          {
            this.alerts.errorDialog(error.error);
          })
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  cancel()
  {
    this.location.back();
  }

}
