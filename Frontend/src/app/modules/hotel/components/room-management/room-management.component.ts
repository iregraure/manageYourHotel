import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Building } from 'src/app/interfaces/buildingInterface';
import { Floor } from 'src/app/interfaces/floorInterface';
import { Room } from 'src/app/interfaces/roomInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { RoomManagementService } from '../../services/room-management.service';

@Component({
  selector: 'app-room-management',
  templateUrl: './room-management.component.html',
  styleUrls: ['./room-management.component.scss']
})
export class RoomManagementComponent implements OnInit {

  buildingList: Building[] = [];

  floorList: Floor[] = [];

  roomList: Room[] = [];

  building: Building;

  floor: Floor;

  room: Room;

  constructor(
    private roomManagementService: RoomManagementService,
    private router: Router,
    private alerts: AlertsService
  ) { }

  ngOnInit(): void 
  {
    this.roomManagementService.getAllBuildings().subscribe(buildings =>
      {
        this.buildingList = buildings;
      },
      error =>
      {
        this.alerts.infoDialog("There are no buildings");
      });
  }

  newBuilding()
  {
    this.router.navigate(['hotel', 'newBuilding']);
  }

  newFloor()
  {
    this.router.navigate(['hotel', 'newFloor']);
  }

  newRoom()
  {
    this.router.navigate(['hotel', 'newRoom']);
  }

  selectBuilding(building: Building)
  {
    this.roomManagementService.getAllFloorsInBuilding(building.name).subscribe(floors =>
      {
        this.building = building;
        this.floorList = floors;
      },
      error =>
      {
        this.alerts.infoDialog("This building doesn't have any floor");
      })
  }

  selectFloor(floor: Floor)
  {
    this.roomManagementService.getAllRoomsInFloor(this.building.name, floor.number).subscribe(rooms =>
      {
        if(rooms.length == 0)
        {
          this.alerts.infoDialog("This floor doesn't have any room");
        }
        else
        {
          this.floor = floor;
          this.roomList = rooms;
        }
      })
  }

  showDetails(roomNumber: number)
  {
    this.roomManagementService.setBuildingName(this.building.name);
    this.router.navigate([`hotel/room/${roomNumber}`]);
  }

}
