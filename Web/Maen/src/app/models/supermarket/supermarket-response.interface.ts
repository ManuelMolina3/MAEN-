export interface SupermarketResponse {
    content: Supermarket[];
    size: number;
    totalElements: number;
    pageNumber: number;
    first: boolean;
    last: boolean;
}
export interface Supermarket{
    id: string;
    name: string;
    logotype: string;
    numOfProduct: number;
}
