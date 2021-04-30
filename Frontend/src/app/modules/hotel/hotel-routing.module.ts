import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientDetailsComponent } from './components/client-details/client-details.component';
import { ClientsManagementComponent } from './components/clients-management/clients-management.component';
import { MainHotelComponent } from './components/main-hotel/main-hotel.component';
import { NewClientComponent } from './components/new-client/new-client.component';

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
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HotelRoutingModule { }
