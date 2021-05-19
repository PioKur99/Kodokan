import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Training } from '../data/training/training';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  constructor(private http: HttpClient) { }

  getTrainingShedule(): Observable<Training[]>{
    return //FIXME: this.http.get<Training[]>(`http://localhost:4200/api/receptionists/${id}/visits`)
  }
}
