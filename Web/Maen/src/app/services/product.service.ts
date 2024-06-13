import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError} from 'rxjs';
import { Product, ProductResponse } from '../models/product/product-response.interface';
import { environment } from '../../environments/environment.development';
import { AddProductDTO, EditProduct } from '../models/product/add-product-dto.interface';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private authTokenKey = 'authToken';
  constructor(private http: HttpClient) { }

  getAll(page : number): Observable<ProductResponse> {
    return this.http.get<ProductResponse>(`${environment.apiBaseUrl}/product/all?page=${page}`, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    }).pipe(
      catchError(this.handleError)
    )
  
  }
  createNewProduct(createProduct: AddProductDTO): Observable<Product>{
    return this.http.post<Product>(`${environment.apiBaseUrl}/product/`,
      {
        name: createProduct.name,
        image: createProduct.image,
        brand: createProduct.brand,
        price: createProduct.price,
        priceKg: createProduct.priceKg,
        taxes: createProduct.taxes,
        supermarketId: createProduct.supermarketId,
      },
      {
        headers: new HttpHeaders({
          accept: 'application/json',
          'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
        })
      }).pipe(
        catchError(this.handleError)
      )
  }
  editProduct(id: string, productEdit: EditProduct): Observable<Product>{
    return this.http.put<Product>(`${environment.apiBaseUrl}/product/${id}`,{
      name: productEdit.name,
      image: productEdit.image,
      brand: productEdit.brand,
      price: productEdit.price,
      taxes: productEdit.taxes
    },{
      headers: new HttpHeaders({
        accept: 'application/json',
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    }).pipe(
      catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError('Something bad happened; please try again later.');
  }
}
