import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as config from '../../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class FamilyMemberService {

  
  constructor(private http: HttpClient) {}

  findCustomerById(id: String) : Observable<any> {
    return this.http.get(config.address+"customer"+id);
  }

  findUsers(name: String) : Observable<any> {
    return this.http.get('https://api.celebrityninjas.com/v1/search?name='+name, {
      headers: new HttpHeaders({
        'X-Api-Key': 'Tk7ELmpY47la9onE7aw+nw==mZffkSf3J4ZkYyfa'
      })
    });
  }

  addRelation(customer_id_1: String, customer_id_2: String, relation: String) : Observable<any> {
    return this.http.post(config.address+"family-member/add", {customer_id_1: customer_id_1, customer_id_2: customer_id_2, relation: relation});
  }
}
