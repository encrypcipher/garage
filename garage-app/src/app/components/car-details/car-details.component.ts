import { Component, OnInit } from '@angular/core';
import { CarService } from 'src/app/service/car.service';
@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {
  carDetails: any;
  constructor(private carService: CarService) { }

  ngOnInit(): void {
    this.carDetails = this.carService.getdetails();
  }

}
