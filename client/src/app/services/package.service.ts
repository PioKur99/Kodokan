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
    return this.http.get<PackageDetails>(config.address+'package/get?packageId='+ package_id)
    //TEST
    // let customer_list: Customer[] = [{customerId: 1,
    //   firstName: "Alex",
    //   lastName: "Bord"},
    //   {customerId: 2,
    //     firstName: "Charlote",
    //     lastName: "Dwayne"}]
    // let package_details : PackageDetails = {
    //   name: "nazwa karnetu",
    //   price: "30,43",
    //   owners: customer_list,
    //   endDate: "dsad",
    //   end_date: new Date("2021-11-02"),
    //   paid: true,
    //   purchaseDate:"sadf",
    //   purchase_date: new Date("2019-07-03"),
    //   usedEntries: 1,
    //   remainingEntries: 3,
    //   duration: 30}
    // return new Observable(x => { x.next(package_details)})
  }
}
