import { Component, OnInit } from '@angular/core';
import { CarService } from 'src/app/service/car.service';
import { Car } from 'src/app/interface/Car';
@Component({
  selector: 'app-car-board',
  templateUrl: './car-board.component.html',
  styleUrls: ['./car-board.component.css']
})
export class CarBoardComponent implements OnInit {
  cartProductList = [];
  cars: Array<Car> = [];

  constructor(private carService: CarService) { }

  ngOnInit(): void {
    this.carService.getCars().subscribe(data => {
      console.log(data);
      this.cars = data;
    });
  }
  addProductToCart(product) {
    const productExistInCart = this.cartProductList.find(({ id }) => id === product.id); // find product by name
    if (!productExistInCart) {
      this.cartProductList.push({ ...product, num: 1 }); // enhance "porduct" opject with "num" property
      return;
    }
    productExistInCart.num += 1;
  }
  removeProduct(product) {
    this.cartProductList = this.cartProductList.filter(({ id }) => id !== product.id)
  }

}
