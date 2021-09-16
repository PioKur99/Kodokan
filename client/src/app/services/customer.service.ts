import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AnyARecord } from 'node:dns';
import { from, Observable } from 'rxjs';
import * as config from '../../assets/config.json'
import { CardState } from '../data/card/card-state';
import { Customer } from '../data/customer/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  getCustomers(search_customer?): Observable<Customer[]> {
    if(search_customer)
      return this.http.post<Customer[]>(config.address + 'customers',search_customer)
    else 
      return this.http.post<Customer[]>(config.address + 'customers',{})
    
      //TEST
    // let customerList=[
    //   {customerID: 1, firstName: 'Ali', lastName: 'Muhammad'},
    //   {customerID: 2, firstName: 'Bali', lastName: 'Chill'}]
    // return new Observable(x => { x.next(customerList)})
  }

  deleteCustomer(customer_id: number): Observable<Customer> {
    return this.http.delete<Customer>(config.address + "customers/delete/" + customer_id)
    //TEST
    // let Customer={customerID: 1, firstName: 'Ali', lastName: 'Muhammad'}
    // return new Observable(x => { x.next(Customer)})
  }

  getCardState(id: number): Observable<CardState>{
    return this.http.get<CardState>(config.address+'customer/'+id+'/operations')
  }

  switchCardState(id: number,switchstring: string): Observable<CardState>{
    return this.http.put<CardState>(config.address + 'customer/'+id+'/change-card-state/'+ switchstring.toUpperCase(),{})
    //TEST
    // let cardstate = new CardState(null,'NOT_PAID','PAID')
    // if(switchstring==="up")
    //   cardstate = new CardState('PAID','PRINTED','READY')
    // return new Observable(x => { x.next(cardstate)})
  }
  
  postCardId(customer_id: number, card_id: number):Observable<Number>{
    return this.http.post<Number>(config.address + 'customer/' + customer_id + '/add-card/' + card_id,{})
  }

}
