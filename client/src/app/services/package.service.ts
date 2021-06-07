import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as config from '../../assets/config.json'
import { PackageDetails } from '../data/package/package-details';

@Injectable({
  providedIn: 'root'
})
export class PackageService {

  constructor(private http: HttpClient) { }

  getPackageDetails(package_id: number): Observable<PackageDetails>{
    return this.http.get<PackageDetails>(config.address+'package_details/'+package_id)
  }
}
