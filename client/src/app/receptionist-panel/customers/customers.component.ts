import { Component, OnInit, ViewChild } from '@angular/core';
import { Customer } from '../../data/Customer'
import { CustomerService } from '../../service/customer.service'
import { IgxDialogActionsDirective } from 'igniteui-angular/lib/dialog/dialog.directives'
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  customerList: Customer[] // błąd 
  customer_id:number
  deletedCustomer: Customer

  @ViewChild("dialog") dialog; // do dialog.open()/dialog.close() 

  constructor(private CustomerService: CustomerService, private router: Router) { }
  deleteCustomer(/*customer_id: number*/): void {
    this.CustomerService.deleteCustomer(this.customer_id).subscribe(
      x => {
        this.deletedCustomer=x
        console.log("x");
      });

    this.dialog.close();
  }

  errorData() {
    this.dialog.open();
  }

  errorDataLink(): void {
    this.router.navigate(["/receptionist-panel"])
  }

  ngOnInit(): void {
    this.CustomerService.getCustomers().subscribe(
      data => this.customerList = data,
      error => this.errorData(),
    );
  }

}
