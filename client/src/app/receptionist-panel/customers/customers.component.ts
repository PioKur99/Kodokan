import { Component, OnInit, ViewChild } from '@angular/core';
import { DeleteCustomer, customerClass } from '../../data/Customer'
import { CustomerService } from '../../service/customer.service'
import { IgxDialogActionsDirective } from 'igniteui-angular/lib/dialog/dialog.directives'
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  customerList[]: customerClass // błąd 
  deleteId: DeleteCustomer = {
    id: null,
  }

  @ViewChild("dialog") dialog; // do dialog.open()/dialog.close() 
  @ViewChild("errorData") #errorData;

  constructor(private CustomerService: CustomerService, private router: Router) { }
  deleteCustomerFunc(): void {
    this.CustomerService.deleteService(this.deleteId).subscribe(
      x => {
        console.log("x");
      });

    this.dialog.close();
  }

  getId(): void {
    this.dialog.open();
    var cos = 1; // póki co jest to byle co, pozniej tu bedzie pobierane id danego klienta
    this.deleteId.id = cos;
  }

  errorData(): void {
    this.#errorData.open();
  }

  errorDataLink(): void {
    this.router.navigate(["/receptionist-panel"])
  }

  ngOnInit(): void {
    this.CustomerService.getCustomer().subscribe(
      data => this.customerList[] = data, //błąd przez błąd z linii 14
      error => this.errorData(),
    );
  }

}
