import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { DeleteCustomer, customerClass, sendCustomer } from '../data/Customer'
import { HttpClient } from '@angular/common/http';
import { address } from '../../assets/config.json'
//import * as config from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http: HttpClient) {
  }

  deleteService(deleteCustomer: DeleteCustomer): Observable<any> {
    return this.http.post<DeleteCustomer>(address, deleteCustomer)
  }

  getCustomer(): Observable<customerClass> {
    return this.http.get<customerClass>(address + 'customers')
  }

  sendCustomer(sendCustomer: sendCustomer): Observable<any> {
    return this.http.post<sendCustomer>(address, sendCustomer)
  }
}
