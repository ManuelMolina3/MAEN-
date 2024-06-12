import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SupermarketResponse } from '../models/supermarket/supermarket-response.interface';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SupermarketService {
  private authTokenKey = 'authToken';

  constructor(private http: HttpClient) { }

  getAll(page : number): Observable<SupermarketResponse> {
    return this.http.get<SupermarketResponse>(`${environment.apiBaseUrl}/supermarket/?page=${page}`, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    })
  }
}
