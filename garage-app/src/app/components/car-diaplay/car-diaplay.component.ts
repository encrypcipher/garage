import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { CarService } from 'src/app/service/car.service';
import { Subject } from 'rxjs';
import { Car } from 'src/app/interface/Car';
import { Router } from '@angular/router';
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
  @Input() products: Array<Car> = [];
  loaded: boolean;
  @Output() productAdded = new EventEmitter();

  constructor(private carService: CarService,
    private router: Router,) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers'
    };
  }

  ngOnChanges() {
    if (this.products.length > 0) {
      this.dtTrigger.next();
    }
  }


  addProductToCart(product) {
    this.productAdded.emit(product);
  }

  showDetails(details) {
    this.carService.setdetails(details);
    this.router.navigate(['/details']);
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }

}
