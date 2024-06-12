import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { Product, ProductResponse } from '../models/product/product-response.interface';
import { environment } from '../../environments/environment.development';
import { AddProductDTO } from '../models/product/add-product-dto.interface';


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
    })
  
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
        category: createProduct.category,
        supermarketId: createProduct.supermarketId,
      },
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
        }
      })
  }
}
