import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
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
    }).pipe(
      catchError(this.handleError)
    );
  }
  getAllCompanies(): Observable<Company[]>{
    return this.http.get<Company[]>(`${environment.apiBaseUrl}/company/all`, {
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
        headers: new HttpHeaders({
          'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
        })
      }).pipe(
        catchError(this.handleError)
      );
  }
  editCompany(id: string, companyEdit: AddCompanyDTO): Observable<Company>{
    return this.http.put<Company>(`${environment.apiBaseUrl}/company/${id}`,{
      name: companyEdit.name,
      logotype: companyEdit.logotype
    },{
      headers: new HttpHeaders({
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
