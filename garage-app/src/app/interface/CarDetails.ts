export class CarDetails {
    carId: number;
    warehouse: string;
    lat: string;
    long: string
    constructor(carId: number, warehouse: string, lat: string, long: string) {
        this.carId = carId;
        this.warehouse = warehouse;
        this.lat = lat;
        this.long = long
    }
}