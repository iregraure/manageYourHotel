import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Building } from 'src/app/interfaces/buildingInterface';
import { Floor } from 'src/app/interfaces/floorInterface';
import { Room } from 'src/app/interfaces/roomInterface';
import { RoomState } from 'src/app/interfaces/roomStateInterface';
import { Stay } from 'src/app/interfaces/stayInterface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RoomManagementService {

  buildingName: string;

  constructor(private http: HttpClient) { }

  getAllBuildings(): Observable<Building[]>
  {
    return this.http.get<Building[]>(environment.getBuildingsUrl);
  }

  getAllFloorsInBuilding(buildingName: String): Observable<Floor[]>
  {
    return this.http.get<Floor[]>(`/building/${buildingName}/floors`);
  }

  createBuilding(building: Building)
  {
    return this.http.post<Building>(environment.createBuildingUrl, building);
  }

  getAllRoomsInFloor(buildingName: String, floorNumber: number): Observable<Room[]>
  {
    return this.http.get<Room[]>(`/floor/${buildingName}/${floorNumber}/rooms`);
  }

  createFloor(floor: Floor)
  {
    return this.http.post<Floor>(environment.createFloorUrl, floor);
  }

  getAllRoomsInBuilding(buildingName: String): Observable<Room[]>
  {
    return this.http.get<Room[]>(`/room/${buildingName}/rooms`);
  }

  getRoom(buildingName: String, roomNumber: number): Observable<Room>
  {
    return this.http.get<Room>(`/room/${buildingName}/${roomNumber}`);
  }

  createRoom(room: Room)
  {
    return this.http.post<Room>(environment.createRoomUrl, room);
  }

  updateRoom(buildingName: String, room: Room)
  {
    return this.http.put<Room>(`/room/${buildingName}`, room);
  }

  changeState(buildingName: String, roomNumber: number, state: RoomState)
  {
    return this.http.put<RoomState>(`/room/${buildingName}/${roomNumber}`, state);
  }

  addStay(buildingName: String, stay: Stay)
  {
    return this.http.post<Stay>(`/room/${buildingName}/addStay`, stay);
  }

  getAllRoomStates(): Observable<RoomState[]>
  {
    return this.http.get<RoomState[]>(environment.getCreateRoomStateUrl);
  }

  addRoomState(state: RoomState)
  {
    return this.http.post<RoomState>(environment.getCreateRoomStateUrl, state);
  }

  setBuildingName(buildingName: string)
  {
    this.buildingName = buildingName;
  }

  getBuildingName(): string
  {
    return this.buildingName;
  }

}