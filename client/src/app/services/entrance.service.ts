import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Entrance } from '../data/entrance/entrance';
import { EntranceToAdd } from '../data/entrance/entrance-to-add';
import * as config from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class EntranceService {

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
  actionUrl: string
  constructor(private http: HttpClient) { 
    this.actionUrl=config.address
  }

  postEntrance(newEntrance: EntranceToAdd): Observable<number>{
    
    return this.http.post<number>(this.actionUrl+'entrance/add',newEntrance)
  }
}
