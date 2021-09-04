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
    //return this.http.get<CardState>(config.address+'card-state/'+ id)
    let cardtate = new CardState(1,2,3)
    return new Observable(x=> x.next(cardtate))
  }

  getCustomers():Observable<Customer[]>{
    //return this.http.post<Customer[]>(config.address+'customers',{})
    let customerList=[
      {customerID: 1, firstName: 'Ali', lastName: 'Muhammad'},
      {customerID: 2, firstName: 'Bali', lastName: 'Chill'}]
    return new Observable(x => { x.next(customerList)})
  }

  switchCardState(id: number,switchstring: string): Observable<CardState>{
    //return this.http.post<CardState>(config.address + 'set-card-state', {id,switchstring})
    let cardstate = new CardState(null,1,2)
    if(switchstring==="up")
      cardstate = new CardState(2,3,4)
    return new Observable(x => { x.next(cardstate)})
  }
  
  postCardId(customer_id: number, card_id: number):Observable<Number>{
    //return this.http.post<Number>(config.address + 'set-card-id',{card_id,customer_id})
    return new Observable(x=>{x.next(card_id)})
  }

}
