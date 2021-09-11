import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AnyARecord } from 'node:dns';
import { Observable } from 'rxjs';
import * as config from '../../assets/config.json'
import { CardState } from '../data/card/card-state';
import { Customer } from '../data/customer/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  getCardState(id: number): Observable<CardState>{
    return this.http.get<CardState>(config.address+'customer/'+id+'/operations')
  }

  getCustomers():Observable<Customer[]>{
    //TODO: nie działa wywala 500 Internal Server Error
    //return this.http.post<Customer[]>(config.address+'customers',{})
    //TEST
    let customerList=[
      {customerID: 1, firstName: 'TEST Ali', lastName: 'Muhammad'},
      {customerID: 2, firstName: 'TEST Bali', lastName: 'Chill'}]
    return new Observable(x => { x.next(customerList)})
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
