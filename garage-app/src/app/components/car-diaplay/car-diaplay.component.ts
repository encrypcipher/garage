import { Component, OnInit } from '@angular/core';
import { CarService } from 'src/app/service/car.service';
import { Subject } from 'rxjs';
import { Car } from 'src/app/interface/Car';
@Component({
  selector: 'app-car-diaplay',
  templateUrl: './car-diaplay.component.html',
  styleUrls: ['./car-diaplay.component.css']
})
export class CarDiaplayComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  // We use this trigger because fetching the list of items can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<any> = new Subject();
  cars: Array<Car> = [];

  constructor(private carService: CarService) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers'
    };
    this.carService.getCars().subscribe(data => {
      console.log(data);
      this.cars = data;
      this.dtTrigger.next();
    });
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

}
