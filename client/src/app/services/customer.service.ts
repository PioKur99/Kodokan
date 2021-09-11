import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { Customer } from '../data/customer/Customer'
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
    return this.http.delete<Customer>(address + "customers/delete/" + customer_id)
    //TEST
    // let Customer={customerID: 1, firstName: 'Ali', lastName: 'Muhammad'}
    // return new Observable(x => { x.next(Customer)})
  }

  getCustomers(search_customer): Observable<Customer[]> {
    return this.http.post<Customer[]>(address + 'customers',search_customer)
    
    //TEST
    // let customerList=[
    //   {customerID: 1, firstName: 'Ali', lastName: 'Muhammad'},
    //   {customerID: 2, firstName: 'Bali', lastName: 'Chill'}]
    // return new Observable(x => { x.next(customerList)})
  }

}
