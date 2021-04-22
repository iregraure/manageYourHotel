import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientsManagementComponent } from './components/clients-management/clients-management.component';
import { MainHotelComponent } from './components/main-hotel/main-hotel.component';

import { HotelComponent } from './hotel.component';

const routes: Routes = [
  { 
    path: '', 
    component: MainHotelComponent 
  },
  { 
    path: 'clients', 
    component: ClientsManagementComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HotelRoutingModule { }
