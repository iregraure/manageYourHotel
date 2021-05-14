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

// Material imports
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatSortModule } from '@angular/material/sort';

// Other imports
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [HotelComponent, MainHotelComponent, ClientsManagementComponent, NewClientComponent, ClientDetailsComponent],
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
    MatSortModule
  ]
})
export class HotelModule { }
