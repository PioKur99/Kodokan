import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NewClient } from '../data/client/new-client';
import * as config from '../../assets/config.json';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http:HttpClient) { }

  registerUser(client:NewClient):Observable<any>{
    return this.http.post<any>(config.address + "members", {
      "firstName": client.name,
      "lastName": client.surname,
      "pesel": client.pesel
    })
  }
}
