import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ContractResponse } from '../models/contract/contract-response.interface';

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  private authTokenKey = 'authToken';
  constructor(private http: HttpClient) { }

  getAll(page : number): Observable<ContractResponse> {
    return this.http.get<ContractResponse>(`${environment.apiBaseUrl}/contract/?page=${page}`, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    })
  }
}
