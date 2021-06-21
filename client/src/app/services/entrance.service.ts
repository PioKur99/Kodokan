import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Entrance } from '../data/entrance/entrance';
import * as config from '../../assets/config.json';

@Injectable({
  providedIn: 'root'
})
export class EntranceService {

  constructor(private http: HttpClient) { }

  getEntrances(): Observable<Entrance[]>{
    return this.http.get<Entrance[]>(config.address + 'entrances')
    // let entrance=[{
    //   entrance_id: 1,
    //   name: "Marian",
    //   surname: "Paździoch",
    //   date: new Date(),
    //   package: "Nazwa pakietu",
    //   training: "nazwa treningu",
    //   card_id: 1
    // },
    // {
    //   entrance_id: 2,
    //   name: "Kazimierz",
    //   surname: "Boczek",
    //   date: new Date(),
    //   package: "Nazwa pakietu",
    //   training: "nazwa treningu",
    //   card_id: 2
    // }]
    // return new Observable(x=>{x.next(entrance)})
  }

  deleteEntrance(entrance_id: number): Observable<number>{
    return this.http.delete<number>(config.address + 'deleteEntrance/' + entrance_id)
  }
}
