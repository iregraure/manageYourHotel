import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AlertsType, Types } from '../../interfaces/alerts-type';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.scss']
})
export class AlertsComponent {

  public alertTypeClass = Types;

  constructor(@Inject(MAT_DIALOG_DATA) public data: AlertsType) { }

}
