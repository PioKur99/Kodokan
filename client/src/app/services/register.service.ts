import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../data/client';
import * as config from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http : HttpClient) { }

  addClient(client:Client) : Observable<any> {
    return this.http.post<Client>(config.address+"members", client);
  }

}
