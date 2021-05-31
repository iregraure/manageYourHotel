import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HotelRoutingModule } from './hotel-routing.module';

// Components
import { HotelComponent } from './hotel.component';
import { MainHotelComponent } from './components/main-hotel/main-hotel.component';
import { ClientsManagementComponent } from './components/clients-management/clients-management.component';
import { NewClientComponent } from './components/new-client/new-client.component';
import { ClientDetailsComponent } from './components/client-details/client-details.component';
import { RoomManagementComponent } from './components/room-management/room-management.component';
import { NewBuildingComponent } from './components/new-building/new-building.component';
import { NewFloorComponent } from './components/new-floor/new-floor.component';
import { NewRoomComponent } from './components/new-room/new-room.component';
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { UpdateRoomComponent } from './components/update-room/update-room.component';
import { NewStayComponent } from './components/new-stay/new-stay.component';

// Material imports
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatSortModule } from '@angular/material/sort';
import { MatAutocompleteModule } from '@angular/material/autocomplete';

// Other imports
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [HotelComponent, MainHotelComponent, ClientsManagementComponent, NewClientComponent, ClientDetailsComponent, RoomManagementComponent, NewBuildingComponent, NewFloorComponent, NewRoomComponent, RoomDetailsComponent, UpdateRoomComponent, NewStayComponent],
  imports: [
    CommonModule,
    HotelRoutingModule,
    MatIconModule,
    MatFormFieldModule,
    ReactiveFormsModule, 
    FormsModule,
    MatInputModule,
    MatTableModule,
    MatCardModule,
    NgxPaginationModule,
    MatSortModule,
    MatAutocompleteModule
  ]
})
export class HotelModule { }
