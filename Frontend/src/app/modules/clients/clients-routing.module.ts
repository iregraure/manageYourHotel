import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ClientsComponent } from './clients.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { MainClientComponent } from './components/main-client/main-client.component';
import { PersonalDataComponent } from './components/personal-data/personal-data.component';
import { StayDetailsComponent } from './components/stay-details/stay-details.component';
import { StaysListComponent } from './components/stays-list/stays-list.component';

const routes: Routes = [
  { 
    path: '', 
    component: MainClientComponent
  },
  {
    path: 'personal',
    component: PersonalDataComponent
  },
  {
    path: 'user',
    component: EditUserComponent
  },
  {
    path: 'stays',
    component: StaysListComponent
  },
  {
    path: 'stay/:day/:month/:year',
    component: StayDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientsRoutingModule { }
