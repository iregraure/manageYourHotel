import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { ClientsRoutingModule } from './clients-routing.module';
import { ClientsComponent } from './clients.component';
import { MainClientComponent } from './components/main-client/main-client.component';
import { PersonalDataComponent } from './components/personal-data/personal-data.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { StaysListComponent } from './components/stays-list/stays-list.component';
import { StayDetailsComponent } from './components/stay-details/stay-details.component';

// Material imports
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [ClientsComponent, MainClientComponent, PersonalDataComponent, EditUserComponent, StaysListComponent, StayDetailsComponent],
  imports: [
    CommonModule,
    ClientsRoutingModule,
    ReactiveFormsModule, 
    FormsModule,
    MatIconModule,
    MatCardModule
  ]
})
export class ClientsModule { }
