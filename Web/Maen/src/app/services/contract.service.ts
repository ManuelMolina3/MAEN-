import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { Contract, ContractResponse } from '../models/contract/contract-response.interface';
import { AddContractDTO, EditContractDTO } from '../models/contract/add-contract-dto.interface';

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
    }).pipe(
      catchError(this.handleError)
    );
  }

  createNewContract(createContract: AddContractDTO): Observable<Contract>{
    return this.http.post<Contract>(`${environment.apiBaseUrl}/contract/`,
      {
        priceEnergy:    createContract.priceEnergy,
        discountEnergy: createContract.discountEnergy,
        pricePower:     createContract.pricePower,
        priceEquipment: createContract.priceEquipment,
        taxes:          createContract.taxes,
        nameCompany:    createContract.nameCompany,
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
  deleteContract(id: String): Observable<any> {
    return this.http.delete<any>(`${environment.apiBaseUrl}/contract/${id}`, {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
      })
    }).pipe(
      catchError(this.handleError)
    );
  }
  editContract(id: string, contractEdit: EditContractDTO): Observable<Contract>{
    return this.http.put<Contract>(`${environment.apiBaseUrl}/contract/${id}`,{
      priceEnergy: contractEdit.priceEnergy,
      discountEnergy: contractEdit.discountEnergy,
      pricePower: contractEdit.pricePower,
      priceEquipment: contractEdit.priceEquipment,
      taxes: contractEdit.taxes,
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
