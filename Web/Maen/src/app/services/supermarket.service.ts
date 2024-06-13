import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
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
        headers: new HttpHeaders({
          accept: 'application/json',
          'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
        })
      }).pipe(
        catchError(this.handleError)
      );
  }
  findAll(): Observable<Supermarket[]>{
    return this.http.get<Supermarket[]>(`${environment.apiBaseUrl}/supermarket/all`,{
      headers: new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    }).pipe(
      catchError(this.handleError)
    );
  }
  editSupermarket(id: string, superEdit: AddSupermarketDTO): Observable<Supermarket>{
    return this.http.put<Supermarket>(`${environment.apiBaseUrl}/supermarket/${id}`,{
      name: superEdit.name,
      logotype: superEdit.logotype
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
