export class AddProductDTO {
    name:          string;
    image:         string;
    brand:         string;
    price:         number;
    priceKg:       number;
    taxes:         number;
    category:      number;
    supermarketId: string;
    constructor(name: string, image: string, brand: string, price: number, priceKg: number, taxes: number, category: number, supermarketId: string){
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.price = price;
        this.priceKg = priceKg;
        this.taxes = taxes;
        this.category = category;
        this.supermarketId = supermarketId;
    }
}
