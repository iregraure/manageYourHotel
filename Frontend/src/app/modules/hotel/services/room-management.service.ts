import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Building } from 'src/app/interfaces/buildingInterface';
import { Floor } from 'src/app/interfaces/floorInterface';
import { Room } from 'src/app/interfaces/roomInterface';
import { RoomState } from 'src/app/interfaces/roomStateInterface';
import { Stay } from 'src/app/interfaces/stayInterface';

@Injectable({
  providedIn: 'root'
})
export class RoomManagementService {

  buildingName: string;

  constructor(private http: HttpClient) { }

  getAllBuildings(): Observable<Building[]>
  {
    return this.http.get<Building[]>('http://localhost:8080/building/buildings');
  }

  getAllFloorsInBuilding(buildingName: String): Observable<Floor[]>
  {
    return this.http.get<Floor[]>(`http://localhost:8080/building/${buildingName}/floors`);
  }

  createBuilding(building: Building)
  {
    return this.http.post<Building>('http://localhost:8080/building', building);
  }

  getAllRoomsInFloor(buildingName: String, floorNumber: number): Observable<Room[]>
  {
    return this.http.get<Room[]>(`http://localhost:8080/floor/${buildingName}/${floorNumber}/rooms`);
  }

  createFloor(floor: Floor)
  {
    return this.http.post<Floor>('http://localhost:8080/floor', floor);
  }

  getAllRoomsInBuilding(buildingName: String): Observable<Room[]>
  {
    return this.http.get<Room[]>(`http://localhost:8080/room/${buildingName}/rooms`);
  }

  getRoom(buildingName: String, roomNumber: number): Observable<Room>
  {
    return this.http.get<Room>(`http://localhost:8080/room/${buildingName}/${roomNumber}`);
  }

  createRoom(room: Room)
  {
    return this.http.post<Room>('http://localhost:8080/room', room);
  }

  updateRoom(buildingName: String, room: Room)
  {
    return this.http.put<Room>(`http://localhost:8080/room/${buildingName}`, room);
  }

  changeState(buildingName: String, roomNumber: number, state: RoomState)
  {
    return this.http.put<RoomState>(`http://localhost:8080/room/${buildingName}/${roomNumber}`, state);
  }

  addStay(buildingName: String, stay: Stay)
  {
    return this.http.post<Stay>(`http://localhost:8080/room/${buildingName}/addStay`, stay);
  }

  getAllRoomStates(): Observable<RoomState[]>
  {
    return this.http.get<RoomState[]>('http://localhost:8080/roomState');
  }

  addRoomState(state: RoomState)
  {
    return this.http.post<RoomState>('http://localhost:8080/roomState', state);
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