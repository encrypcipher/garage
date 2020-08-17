import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarBoardComponent } from './car-board.component';

describe('CarBoardComponent', () => {
  let component: CarBoardComponent;
  let fixture: ComponentFixture<CarBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
