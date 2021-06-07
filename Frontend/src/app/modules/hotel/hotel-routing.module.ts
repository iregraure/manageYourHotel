import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginGuardGuard } from 'src/app/guards/login-guard.guard';
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
    component: MainHotelComponent,
    canActivate: [LoginGuardGuard]
  },
  { 
    path: 'clients', 
    component: ClientsManagementComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'newClient',
    component: NewClientComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'client/:dni',
    component: ClientDetailsComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'rooms',
    component: RoomManagementComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'newBuilding',
    component: NewBuildingComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'newFloor',
    component: NewFloorComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'newRoom',
    component: NewRoomComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'room/:number',
    component: RoomDetailsComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'room/:number/update',
    component: UpdateRoomComponent,
    canActivate: [LoginGuardGuard]
  },
  {
    path: 'room/:number/newStay',
    component: NewStayComponent,
    canActivate: [LoginGuardGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HotelRoutingModule { }
