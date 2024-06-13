export class AddProductDTO {
    name:          string;
    image:         string;
    brand:         string;
    price:         number;
    priceKg:       number;
    taxes:         number;
    supermarketId: string;
    constructor(name: string, image: string, brand: string, price: number, priceKg: number, taxes: number, supermarketId: string){
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.price = price;
        this.priceKg = priceKg;
        this.taxes = taxes;
        this.supermarketId = supermarketId;
    }

}
export class EditProduct{
    name:          string;
    image:         string;
    brand:         string;
    price:         number;
    taxes:         number;
constructor(name: string, image: string, brand: string, price: number, taxes: number){
    this.name = name;
    this.image = image;
    this.brand = brand;
    this.price = price;
    this.taxes = taxes;
}
}