import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PackageType } from '../data/package/package-type';
import * as config from '../../assets/config.json'
import { Package } from '../data/package/package';

@Injectable({
  providedIn: 'root'
})
export class PackageService {

  constructor(private http: HttpClient) { }

  getPackageType(): Observable<PackageType[]>{
    return this.http.get<PackageType[]>(config.address+'package_type')
  }

  postAddPackage(pass: Package): Observable<number>{
    return this.http.post<number>(config.address+'add_package',pass)
  }
}
