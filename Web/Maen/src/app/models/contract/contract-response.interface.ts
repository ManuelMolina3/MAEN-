export interface ContractResponse {
    content: Contract[];
    size: number;
    totalElements: number;
    pageNumber: number;
    first: boolean;
    last: boolean;
}
export interface Contract{
    id: string;
    priceEnergy: number;
    discountEnergy: number;
    pricePower: number;
    priceEquipment: number;
    taxes: number;
    companyName: string;
    companyLogotype: string;
    companyId: string;
}
