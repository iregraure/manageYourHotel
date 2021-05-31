import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientDetailsComponent } from './components/client-details/client-details.component';
import { ClientsManagementComponent } from './components/clients-management/clients-management.component';
import { MainHotelComponent } from './components/main-hotel/main-hotel.component';
import { NewBuildingComponent } from './components/new-building/new-building.component';
import { NewClientComponent } from './components/new-client/new-client.component';
import { NewFloorComponent } from './components/new-floor/new-floor.component';
import { NewRoomComponent } from './components/new-room/new-room.component';
import { NewStayComponent } from './components/new-stay/new-stay.component';
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { RoomManagementComponent } from './components/room-management/room-management.component';
import { UpdateRoomComponent } from './components/update-room/update-room.component';

const routes: Routes = [
  { 
    path: '', 
    component: MainHotelComponent 
  },
  { 
    path: 'clients', 
    component: ClientsManagementComponent 
  },
  {
    path: 'newClient',
    component: NewClientComponent
  },
  {
    path: 'client/:dni',
    component: ClientDetailsComponent
  },
  {
    path: 'rooms',
    component: RoomManagementComponent
  },
  {
    path: 'newBuilding',
    component: NewBuildingComponent
  },
  {
    path: 'newFloor',
    component: NewFloorComponent
  },
  {
    path: 'newRoom',
    component: NewRoomComponent
  },
  {
    path: 'room/:number',
    component: RoomDetailsComponent
  },
  {
    path: 'room/:number/update',
    component: UpdateRoomComponent
  },
  {
    path: 'room/:number/newStay',
    component: NewStayComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HotelRoutingModule { }
