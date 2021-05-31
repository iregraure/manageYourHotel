import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Room } from 'src/app/interfaces/roomInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { RoomManagementService } from '../../services/room-management.service';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.scss']
})
export class RoomDetailsComponent implements OnInit {

  room: Room;

  buildingName: string;

  smoker: string;

  tv: string;

  airConditioning: string;

  breakfast: string;

  constructor(private activeRoute: ActivatedRoute,
              private roomManagementService: RoomManagementService,
              private router: Router) { }

  ngOnInit(): void 
  {
    this.buildingName = this.roomManagementService.getBuildingName();

    this.getRoom();
  }

  getRoom()
  {
    let number = this.activeRoute.snapshot.paramMap.get('number');
    let roomNumber: number = Number(number);
    this.roomManagementService.getRoom(this.buildingName, roomNumber).subscribe(room =>
      {
        this.room = room;
        this.fromBooleanToString();
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
    this.router.navigate(['hotel', 'rooms']);
  }

  update()
  {
    this.router.navigate([`hotel/room/${this.room.number}/update`]);
  }

  addStay()
  {
    this.router.navigate([`hotel/room/${this.room.number}/newStay`]);
  }

}
