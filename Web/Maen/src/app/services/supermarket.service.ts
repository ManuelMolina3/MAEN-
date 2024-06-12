import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Supermarket, SupermarketResponse } from '../models/supermarket/supermarket-response.interface';
import { environment } from '../../environments/environment';
import { AddSupermarketDTO } from '../models/supermarket/add-supermarket-dto.interface';

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
  createNewSupermarket(createSupermarket: AddSupermarketDTO): Observable<Supermarket>{
    return this.http.post<Supermarket>(`${environment.apiBaseUrl}/supermarket/`,
      {
        name: createSupermarket.name,
        logotype: createSupermarket.logotype
      },
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
        }
      })
  }
  findAll(): Observable<Supermarket[]>{
    return this.http.get<Supermarket[]>(`${environment.apiBaseUrl}/supermarket/all`,{
      headers: new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    });
  }
}
