import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { first } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Observable, of, merge, Subject, BehaviorSubject, zip } from 'rxjs';
import { map } from 'rxjs/operators';
import { Car } from '../interface/Car';
import { CarDetails } from '../interface/CarDetails';
@Injectable({
  providedIn: 'root'
})
export class CarService {
  constructor(private http: HttpClient) { }
  warehouses: any;
  warehouseVehicles: any;
  carList: Array<Car> = [];
  private details = new BehaviorSubject(null);
  carDetails = this.details.asObservable();

  getWarehouses() {
    return this.http.get(`${environment.apiUrl}`);
  }

  setdetails(data: any) {
    this.details.next(data);
  }

  getdetails(): any {
    return this.carDetails;
  }

  getCars(): Observable<Array<Car>> {
    return this.getWarehouses()
      .pipe(
        map(response => {
          this.warehouses = response;
          this.warehouses.forEach(warehouse => {
            this.warehouseVehicles = warehouse.cars.vehicles;
            this.warehouseVehicles.forEach(vehicle => {
              this.carList.push(new Car(vehicle._id, vehicle.year_model, vehicle.model, vehicle.make, vehicle.price, vehicle.licensed, vehicle.date_added, new CarDetails(vehicle._id, warehouse.name, warehouse.location.lat, warehouse.location.long)));
            });
          });
          this.carList.sort((a, b) => a.dateAdded.localeCompare(b.dateAdded));
          return this.carList
        }));
  }
}
