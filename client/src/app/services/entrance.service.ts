import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import { EntranceToAdd } from '../data/entrance/entrance-to-add';
import * as config from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class EntranceService {
  actionUrl: string
  
  constructor(private http: HttpClient) { 
    this.actionUrl=config.address
  }

  postEntrance(newEntrance: EntranceToAdd): Observable<number>{
    return this.http.post<number>(this.actionUrl+'newEntrance',newEntrance)
  }
}
