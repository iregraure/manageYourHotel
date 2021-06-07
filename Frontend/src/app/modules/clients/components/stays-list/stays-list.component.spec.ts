import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaysListComponent } from './stays-list.component';

describe('StaysListComponent', () => {
  let component: StaysListComponent;
  let fixture: ComponentFixture<StaysListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaysListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StaysListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
