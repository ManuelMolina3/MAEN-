export interface ProductResponse {
    content:       Product[];
    size:          number;
    totalElements: number;
    pageNumber:    number;
    first:         boolean;
    last:          boolean;
}

export interface Product {
    id:               string;
    productName:      string;
    productImage:     string;
    productBrand:     string;
    price:            number;
    taxes:            number;
    superMarketName:  string;
    superMarketImage: string;
}
