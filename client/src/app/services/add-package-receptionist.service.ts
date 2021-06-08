import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as config from '../../assets/config.json'
@Injectable({
  providedIn: 'root'
})
export class AddPackageReceptionistService {

  constructor(private http: HttpClient) {
   }

   findCustomerById(id: String) : Observable<any> {
      return this.http.get(config.address+"customer"+id);
   }
   createPackage(id: String, type_id: String ) : Observable<any> {
    return this.http.post(config.address+"package/create", {customer_id: id, type_id: type_id});
  }
  payForPackage(package_id: String) : Observable<any> {
    return this.http.post(config.address+"package/pay", package_id);
 }
 getPackages() : Observable<any> {
  return this.http.get(config.address+"packages");
}
}
