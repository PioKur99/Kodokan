import { Component, OnInit, ViewChild } from '@angular/core';
import { DeleteCustomer } from '../../data/DeleteCustomer'
import { DeleteCustomerService } from '../../service/delete-customer.service'
import { IgxDialogActionsDirective } from 'igniteui-angular/lib/dialog/dialog.directives'

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  deleteId: DeleteCustomer = {
    id: null,
  }

  @ViewChild("dialog") dialog; // do dialog.open()/dialog.close() 

  constructor(private deleteCustomerService: DeleteCustomerService) { }

  deleteCustomerFunc(): void {
    this.deleteCustomerService.deleteService(this.deleteId).subscribe(
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

  ngOnInit(): void {
  }

}
