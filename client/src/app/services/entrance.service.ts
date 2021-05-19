import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import { EntranceToAdd } from '../data/entrance/entrance-to-add';

@Injectable({
  providedIn: 'root'
})
export class EntranceService {

  constructor() { }

  postEntrance(newEntrance: EntranceToAdd): Observable<number>{
    return //FIXME: poprawić 
    //this.http.post<number>(`http://localhost:8080/api/visits`,newEntrance)
  }
}
