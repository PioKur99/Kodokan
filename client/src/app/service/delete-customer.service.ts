import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { DeleteCustomer } from '../data/DeleteCustomer'
import { HttpClient } from '@angular/common/http';
import { address } from '../../assets/config.json'

@Injectable({
  providedIn: 'root'
})
export class DeleteCustomerService {
  constructor(private http: HttpClient) {
  }

  deleteCustomer(deleteCustomer: DeleteCustomer): Observable<any> {
    return this.http.post<DeleteCustomer>(address, deleteCustomer)
  }

}
