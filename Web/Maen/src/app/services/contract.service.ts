import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Contract, ContractResponse } from '../models/contract/contract-response.interface';
import { AddContractDTO } from '../models/contract/add-contract-dto.interface';

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
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${localStorage.getItem(this.authTokenKey)}`
        }
      })
  }
}
