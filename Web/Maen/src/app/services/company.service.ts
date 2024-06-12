import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company, CompanyResponse } from '../models/company/company-response.interface';
import { environment } from '../../environments/environment';
import { AddCompanyDTO } from '../models/company/add-company-dto.interface';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private authTokenKey = 'authToken';
  constructor(private http: HttpClient) { }
  getAll(page : number): Observable<CompanyResponse> {
    return this.http.get<CompanyResponse>(`${environment.apiBaseUrl}/company/?page=${page}`, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    })
  }
  createNewCompany(createCompany: AddCompanyDTO): Observable<Company>{
    return this.http.post<Company>(`${environment.apiBaseUrl}/company/`,
      {
        name: createCompany.name,
        logotype: createCompany.logotype,
      },
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
        }
      })
  }
}
