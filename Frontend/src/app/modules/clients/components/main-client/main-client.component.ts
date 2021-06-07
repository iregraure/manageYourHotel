import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-client',
  templateUrl: './main-client.component.html',
  styleUrls: ['./main-client.component.scss']
})
export class MainClientComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goUser()
  {
    this.router.navigate(['clients', 'personal']);
  }

  goStays()
  {
    this.router.navigate(['clients', 'stays']);
  }

}
