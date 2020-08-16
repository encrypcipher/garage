import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarDiaplayComponent } from './car-diaplay.component';

describe('CarDiaplayComponent', () => {
  let component: CarDiaplayComponent;
  let fixture: ComponentFixture<CarDiaplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarDiaplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarDiaplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
