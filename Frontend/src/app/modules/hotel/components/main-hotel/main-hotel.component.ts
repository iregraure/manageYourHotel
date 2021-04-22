import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-hotel',
  templateUrl: './main-hotel.component.html',
  styleUrls: ['./main-hotel.component.scss']
})
export class MainHotelComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goClients()
  {
    this.router.navigate(['clients']);
  }

  goRooms()
  {

  }

  goEmployees()
  {

  }

}
