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
    client.image = client.image.slice(client.image.indexOf(",")+1);
    return this.http.post<Client>("http://172.18.0.3:8081/customers/create/2", client);
  }

  addDiscipline(id: String, discipline: String) : Observable<any>{
    return this.http.patch<any>("http://172.18.0.3:8081/discipline", {discipline: discipline, id: id}); 
  }

  getDisciplines() : Observable<any>{
    return this.http.get<any>("http://172.18.0.3:8081/discipline");
  }
}
