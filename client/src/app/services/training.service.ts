import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Training } from '../data/training/training';
import * as config from'../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  constructor(private http: HttpClient) { }

  getTrainingShedule(): Observable<Training[]>{
    return this.http.get<Training[]>(config.address+'trainings/nearest')
  }
}
