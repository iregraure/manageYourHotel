import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Stay } from 'src/app/interfaces/stayInterface';
import { UserManagementService } from '../../services/user-management.service';
import jwt_decode from 'jwt-decode';
import { JwtManagerService } from 'src/app/services/jwt-manager.service';
import { AlertsService } from 'src/app/services/alerts.service';
import { Room } from 'src/app/interfaces/roomInterface';
import { RoomManagementService } from 'src/app/modules/hotel/services/room-management.service';

@Component({
  selector: 'app-stay-details',
  templateUrl: './stay-details.component.html',
  styleUrls: ['./stay-details.component.scss']
})
export class StayDetailsComponent implements OnInit {

  stay: Stay;

  room: Room;

  dni: string;

  smoker: string;

  tv: string;

  airConditioning: string;

  breakfast: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private userService: UserManagementService,
    private location: Location,
    private jwt: JwtManagerService,
    private alerts: AlertsService,
    private roomService: RoomManagementService
  ) { }

  ngOnInit(): void 
  {
    this.getStay();
  }
  
  getStay()
  {
    let jwt = this.jwt.getJwt();
    let decoded = jwt_decode(jwt);
    let dni = decoded['username'];
    let day = this.activatedRoute.snapshot.paramMap.get('day');
    let month = this.activatedRoute.snapshot.paramMap.get('month');
    let year = this.activatedRoute.snapshot.paramMap.get('year');
    let startDate = day + '/' + month + '/' + year;
    this.userService.getStay(dni, startDate).subscribe(res =>
      {
        this.stay = res;
        this.getRoom();
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  getRoom()
  {
    let roomNumber = this.stay.roomNumber;
    this.roomService.getRoom(this.stay.buildingName, roomNumber).subscribe(res =>
      {
        this.room = res;
        this.fromBooleanToString();
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  fromBooleanToString()
  {
    if(this.room.smoker)
    {
      this.smoker = 'Yes';
    }
    else
    {
      this.smoker = 'No';
    }
    if(this.room.tv)
    {
      this.tv = 'Yes';
    }
    else
    {
      this.tv = 'No';
    }
    if(this.room.airConditioning)
    {
      this.airConditioning = 'Yes';
    }
    else
    {
      this.airConditioning = 'No';
    }
    if(this.room.breakfast)
    {
      this.breakfast = 'Yes';
    }
    else
    {
      this.breakfast = 'No';
    }
  }

  back()
  {
    this.location.back();
  }

}
