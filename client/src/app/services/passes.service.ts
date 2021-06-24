import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Passes } from '../data/passes';
import * as config from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class PassesService {

  constructor(private http: HttpClient) { }

  getPasses(): Observable<Passes[]>{
    return this.http.get<Passes[]>(config.address + 'passes')
  }

  deletePasses(package_id: number): Observable<number>{
    return this.http.delete<number>(config.address + 'deletePasses/' + package_id)
  }
}
