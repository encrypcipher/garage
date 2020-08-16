export class CarDetails {
    warehouse: string;
    lat: string;
    long: string
    constructor(warehouse: string, lat: string, long: string) {
        this.warehouse = warehouse;
        this.lat = lat;
        this.long = long
    }
}