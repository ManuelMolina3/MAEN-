export interface CompanyResponse {
    content: Company[];
    size: number;
    totalElements: number;
    pageNumber: number;
    first: boolean;
    last: boolean;
}
export interface Company{
    id: string;
    name: string;
    urlImage: string;
    numOfContract: number;
}
