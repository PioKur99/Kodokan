import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { Customer } from '../data/Customer'
import { HttpClient } from '@angular/common/http';
import { address } from '../../assets/config.json'
//import * as config from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http: HttpClient) {
  }

  deleteCustomer(customer_id: number): Observable<Customer> {
    return this.http.post<Customer>(address, customer_id)
  }

  getCustomers(): Observable<Customer[]> {
    //return this.http.get<Customer[]>(address + 'customers')
    let customerList=[
      {customer_id: 1, first_name: 'Ali', last_name: 'Muhammad'},
      {customer_id: 2, first_name: 'Bali', last_name: 'Chill'}]
    return new Observable(x => { x.next(customerList)})
  }

}
