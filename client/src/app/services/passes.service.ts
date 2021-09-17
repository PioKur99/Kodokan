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

  getPasses(search_package?): Observable<Passes[]>{
    if(search_package)
      return this.http.post<Passes[]>(config.address + 'package',search_package)
    else 
      return this.http.post<Passes[]>(config.address + 'package',{})
    //return this.http.post<Passes[]>(config.address + 'package',{})
    // let passes=[{
    //   package_id: 1,
    //   package_name: 'nazwa karnetu',
    //   first_name: "Marian",
    //   last_name: "Paździoch",
    //   end_date: new Date()
    // },
    // {
    //   package_id: 2,
    //   package_name: 'rodzinny',
    //   first_name: "Bob",
    //   last_name: "Kali",
    //   end_date: new Date()
    // }]
    // return new Observable(x=>{x.next(passes)})
  }

  deletePasses(package_id: number): Observable<number>{
    return this.http.delete<number>(config.address + 'package?packageId=' + package_id)
  }
}
