import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HotelRoutingModule } from './hotel-routing.module';
import { HotelComponent } from './hotel.component';
import { MainHotelComponent } from './components/main-hotel/main-hotel.component';

// Material imports
import { MatIconModule } from '@angular/material/icon';


@NgModule({
  declarations: [HotelComponent, MainHotelComponent],
  imports: [
    CommonModule,
    HotelRoutingModule,
    MatIconModule
  ]
})
export class HotelModule { }
