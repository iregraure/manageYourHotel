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

  cerrarDialogo()
  {
    this.dialog.closeAll();
  }

  dialogCargando()
  {
    this.cerrarDialogo();
    this.dialogConfig.data =
    {
      type: Types.WAITING
    }
    this.dialog.open(AlertsComponent, this.dialogConfig)
  }

  dialogError(errorText: string)
  {
    this.cerrarDialogo();
    this.dialogConfig.data =
    {
      type: Types.ERROR,
      text: errorText
    };
    this.dialog.open(AlertsComponent, this.dialogConfig);
  }

  dialogInfo(infoText: string): Observable<number>
  {
    this.cerrarDialogo();
    this.dialogConfig.data = 
    {
      type: Types.INFO,
      texto: infoText
    };
    const dialogoRef = this.dialog.open(AlertsComponent, this.dialogConfig);
    return dialogoRef.afterClosed();
  }

  dialogoConfirmacion(confirmText: string): Observable<number>
  {
    this.cerrarDialogo();
    this.dialogConfig.data = 
    {
      type: Types.CONFIRM,
      texto: confirmText
    };
    const dialogoRef = this.dialog.open(AlertsComponent, this.dialogConfig);
    return dialogoRef.afterClosed();
  }

  mostrarSnackBar(mensajeAMostrar: string)
  {
    this.snackBar.open(mensajeAMostrar, null,
      {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'bottom'
      });
  }
}

