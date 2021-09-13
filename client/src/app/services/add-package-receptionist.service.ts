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
      return this.http.get(config.address+"customers/findById/" +id);
   }
   createPackage(id: String, type_id: String ) : Observable<any> {
    return this.http.post(config.address+"package/add-new-package", {customerID: id, packageTypeID: type_id});
  }

  payForPackage(id: String) : Observable<any> {
    return this.http.put(config.address+"package/pay-for-package/"+id, {id: id});
  }
  getPackages() : Observable<any> {
    return this.http.get(config.address+"package");
  }
}
