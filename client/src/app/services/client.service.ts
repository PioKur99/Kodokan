import { HttpClient } from '@angular/common/http';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client, Gender } from 'src/app/data/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }
  getClientBaseUrl: string = "http://172.18.0.3:8081/customers/findById/"

  getClient(id: number) : Observable<Client>{
    //return this.http.get<Client>(this.getClientBaseUrl + id);
    return this.http.get<Client>("http://date.jsontest.com/");
  }

}
