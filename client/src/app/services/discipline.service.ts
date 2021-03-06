import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

  constructor(private http : HttpClient) { }

  getDisciplines() : Observable<any>{
    return this.http.get<any>("http://172.18.0.3:8081/discipline")
  }
  sendDiscipline(discipline_) : Observable<any>{
    return this.http.patch<any>("http://172.18.0.3:8081/discipline", {discipline: discipline_,id:2})
  }
 
}
