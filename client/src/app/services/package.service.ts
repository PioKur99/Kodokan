import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import * as config from '../../assets/config.json'
import { Customer } from '../data/customer/customer';
import { PackageDetails } from '../data/package/package-details';

@Injectable({
  providedIn: 'root'
})
export class PackageService {

  constructor(private http: HttpClient) { }

  getPackageDetails(package_id: number): Observable<PackageDetails>{
    //return this.http.get<PackageDetails>(config.address+'package_details/'+package_id)
    let customer_list: Customer[] = [{customer_id: 1,
      first_name: "Alex",
      last_name: "Bord"},
      {customer_id: 2,
        first_name: "Charlote",
        last_name: "Dwayne"}]
    let package_details : PackageDetails = {name: "Karnet",
      prize: "30,43",
      CustomerList: customer_list,
      endDate: new Date("7.12.2020"),
      isPaid: true,
      purchaseDate: new Date("12.11.2020"),
      entrance_used: 1,
      entrance_unused: 3,
      duration: 30}
    return new Observable(x => { x.next(package_details)})
  }
}
