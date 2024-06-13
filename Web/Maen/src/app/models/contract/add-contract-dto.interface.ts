export class AddContractDTO {
    priceEnergy:    number;
    discountEnergy: number;
    pricePower:     number;
    priceEquipment: number;
    taxes:          number;
    nameCompany:    string;
    constructor(priceEnergy: number, discountEnergy: number, pricePower: number, priceEquipment: number, taxes: number, nameCompany: string){
        this.priceEnergy = priceEnergy;
        this.discountEnergy = discountEnergy;
        this.pricePower = pricePower;
        this.priceEnergy = priceEnergy;
        this.priceEquipment = priceEquipment;
        this.taxes = taxes;
        this.nameCompany = nameCompany;
    }
}
export class EditContractDTO{
    priceEnergy:    number;
    discountEnergy: number;
    pricePower:     number;
    priceEquipment: number;
    taxes:          number;
    constructor(priceEnergy: number, discountEnergy: number, pricePower: number, priceEquipment: number, taxes: number, nameCompany: string){
        this.priceEnergy = priceEnergy;
        this.discountEnergy = discountEnergy;
        this.pricePower = pricePower;
        this.priceEnergy = priceEnergy;
        this.priceEquipment = priceEquipment;
        this.taxes = taxes;
    }
}
