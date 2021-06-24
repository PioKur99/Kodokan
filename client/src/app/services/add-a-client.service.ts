import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../data/client';

@Injectable({
  providedIn: 'root'
})
export class AddAClientService {

  constructor(private http: HttpClient) { }

  addClient(client: Client) : Observable<any>{
    return this.http.post<Client>("http://172.18.0.3:8081/customers/create", client);
  }

  addDiscipline(id: String, discipline: String) : Observable<any>{
    return this.http.post<any>("http://172.18.0.3:8081/disciplines", {customer_id: id, discipline: discipline}); 
  }
}
