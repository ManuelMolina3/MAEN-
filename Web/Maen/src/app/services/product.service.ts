import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { ProductResponse } from '../models/product/product-response.interface';
import { environment } from '../../environments/environment.development';


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
}
