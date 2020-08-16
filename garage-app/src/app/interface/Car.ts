import { CarDetails } from './CarDetails';

export class Car {
    id: number;
    year: number;
    model: string;
    make: string;
    price: string;
    licensed: boolean;
    dateAdded: string;
    carDetails: CarDetails;

    constructor(id: number, year: number, model: string, make: string, price: string, licensed: boolean, dateAdded: string, carDetails: CarDetails) {
        this.id = id;
        this.year = year;
        this.model = model;
        this.make = make;
        this.price = price;
        this.licensed = licensed;
        this.dateAdded = dateAdded;
        this.carDetails = carDetails
    }
}