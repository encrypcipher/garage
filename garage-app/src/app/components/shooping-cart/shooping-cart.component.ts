import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-shooping-cart',
  templateUrl: './shooping-cart.component.html',
  styleUrls: ['./shooping-cart.component.css']
})
export class ShoopingCartComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() products: any[];
  @Output() productRemoved = new EventEmitter();
  calcTotal() {
    return this.products.reduce((acc, prod) => acc += prod.num, 0)
  }
  removeProduct(product) {
    this.productRemoved.emit(product)
  }

  calcTotalPrice() {
    return this.products.reduce((acc, pr) => acc += pr.price * pr.num, 0).toLocaleString('en-US', { style: 'currency', currency: 'USD' });
  }

}
