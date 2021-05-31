import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Client } from 'src/app/interfaces/clientInterface';
import { Stay } from 'src/app/interfaces/stayInterface';
import { AlertsService } from 'src/app/services/alerts.service';
import { ClientsManagementService } from '../../services/clients-management.service';
import { RoomManagementService } from '../../services/room-management.service';

@Component({
  selector: 'app-new-stay',
  templateUrl: './new-stay.component.html',
  styleUrls: ['./new-stay.component.scss']
})
export class NewStayComponent implements OnInit {

  stayForm: FormGroup;
  buildingName: string;
  roomNumber: number;
  clientList: Client[] = [];
  dniList: string[] = [];
  filteredClients: Observable<string[]>;

  constructor(private activeRoute: ActivatedRoute,
              private roomManagementService: RoomManagementService,
              private clientsManagementService: ClientsManagementService,
              private alerts: AlertsService,
              private location: Location) { }

  ngOnInit(): void 
  {
    this.buildingName = this.roomManagementService.getBuildingName();
    this.roomNumber = Number(this.activeRoute.snapshot.paramMap.get('number'));
    this.getClients();
    this.getDnis();

    this.stayForm = new FormGroup(
      {
        buildingName: new FormControl({value: this.buildingName, disabled: true}, Validators.required),
        roomNumber: new FormControl({value: this.roomNumber, disabled: true}, Validators.required),
        startDate: new FormControl(''),
        endDate: new FormControl(''),
        clientDni: new FormControl('')
      }
    )
  }

  getClients()
  {
    this.clientsManagementService.getAllClients().subscribe(res =>
      {
        this.clientList = res;
      },
      error =>
      {
        this.alerts.errorDialog(error.error);
      })
  }

  getDnis()
  {
    this.clientList.forEach(client => 
      {
        this.dniList.push(client.dni);
      });
  }

  addStay()
  {
    let correctStartDate = this.checkIfDateCorrect(this.stayForm.value.startDate);
    let correctEndDate = this.checkIfDateCorrect(this.stayForm.value.endDate);

    if(correctStartDate && correctEndDate)
    {
      let stay: Stay = 
      {
        startDate: this.stayForm.value.startDate,
        endDate: this.stayForm.value.endDate,
        roomNumber: this.roomNumber,
        clientDni: this.stayForm.value.clientDni
      }
      this.roomManagementService.addStay(this.buildingName, stay).subscribe(res =>
        {
          this.alerts.showSnackbar("Stay added");
          this.location.back();
        },
        error =>
        {
          this.alerts.errorDialog(error.error);
        })
    }
    else
    {
      this.alerts.errorDialog("Dates are no correct. Correct format dd/mm/yyyy");
    }
  }

  cancel()
  {
    this.location.back();
  }

  checkIfDateCorrect(date: string): boolean
  {
    let correct: boolean = true;
    let dateParts = date.split('/');
    if(Number(dateParts[0]) > 0 && Number(dateParts[0]) < 32 && Number(dateParts[1]) > 0 && Number(dateParts[1]) < 13  && Number(dateParts[2]) > 0)
    {
      if(Number(dateParts[1]) == 2 && Number(dateParts[0]) > 28)
      {
        correct = false;
      }
      else if((Number(dateParts[1]) == 4 || Number(dateParts[1]) == 6 || Number(dateParts[1]) == 9 || Number(dateParts[1]) == 11) && Number(dateParts[0]) == 31)
      {
        correct = false
      }
    }
    else
    {
      correct = false;
    }
    return correct;
  }

}
