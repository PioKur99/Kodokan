import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../data/client';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  addClient(client: Client) : Observable<any>{
    client.image = client.image.slice(client.image.indexOf(",")+1);
    return this.http.post<Client>("http://172.18.0.3:8081/customers/create/10", client);
    
  }
}
