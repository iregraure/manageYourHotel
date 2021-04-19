import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainHotelComponent } from './main-hotel.component';

describe('MainHotelComponent', () => {
  let component: MainHotelComponent;
  let fixture: ComponentFixture<MainHotelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainHotelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MainHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
