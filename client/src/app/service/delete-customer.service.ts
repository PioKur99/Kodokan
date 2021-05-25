import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { DeleteCustomer } from '../data/DeleteCustomer'
import { HttpClient } from '@angular/common/http';
import { address } from '../../assets/config.json'
//import * as config from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class DeleteCustomerService {
  constructor(private http: HttpClient) {
  }

  deleteService(deleteCustomer: DeleteCustomer): Observable<any> {
    return this.http.post<DeleteCustomer>(address, deleteCustomer)
  }

  getCustomer(): Observable</*klasa z data */>{
    return this.http.get</*klasa z data */>(address+'customers')
  }

  getCustomer(/*klasa wejściowa*/): Observable</*klasa z data */>{
    return this.http.get</*klasa z data */>(address+'customers',)
  }
}
