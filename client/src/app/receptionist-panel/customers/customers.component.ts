import { Component, OnInit } from '@angular/core';
import { DeleteCustomer } from '../../data/DeleteCustomer'

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  deleteCustomer: DeleteCustomer = {
    id: null,
  }

  constructor() { }

  ngOnInit(): void {
  }

}
