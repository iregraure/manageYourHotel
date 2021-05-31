import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewStayComponent } from './new-stay.component';

describe('NewStayComponent', () => {
  let component: NewStayComponent;
  let fixture: ComponentFixture<NewStayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewStayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewStayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
