import { Injectable } from '@angular/core';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Types } from '../interfaces/alerts-type';
import { AlertsComponent } from '../components/alerts/alerts.component';

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  dialogConfig = new MatDialogConfig();

  constructor(private dialog: MatDialog,
              private snackBar: MatSnackBar) 
  { 
    this.dialogConfig.disableClose = true;
    this.dialogConfig.autoFocus = true;
  }

  closeDialog()
  {
    this.dialog.closeAll();
  }

  loadingDialog()
  {
    this.closeDialog();
    this.dialogConfig.data =
    {
      type: Types.WAITING
    }
    this.dialog.open(AlertsComponent, this.dialogConfig)
  }

  errorDialog(errorText: string)
  {
    this.closeDialog();
    this.dialogConfig.data =
    {
      type: Types.ERROR,
      text: errorText
    };
    this.dialog.open(AlertsComponent, this.dialogConfig);
  }

  infoDialog(infoText: string): Observable<number>
  {
    this.closeDialog();
    this.dialogConfig.data = 
    {
      type: Types.INFO,
      texto: infoText
    };
    const dialogoRef = this.dialog.open(AlertsComponent, this.dialogConfig);
    return dialogoRef.afterClosed();
  }

  confirmDialog(confirmText: string): Observable<number>
  {
    this.closeDialog();
    this.dialogConfig.data = 
    {
      type: Types.CONFIRM,
      texto: confirmText
    };
    const dialogoRef = this.dialog.open(AlertsComponent, this.dialogConfig);
    return dialogoRef.afterClosed();
  }

  showSnackbar(message: string)
  {
    this.snackBar.open(message, null,
      {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'bottom'
      });
  }
}

