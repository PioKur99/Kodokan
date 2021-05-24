import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as config from '../../assets/config.json'
import { CardState } from '../data/card-state/card-state';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  getCardState(id: number): Observable<CardState>{
    return this.http.get<CardState>(config.address+'card-state/'+id)
  }
}
