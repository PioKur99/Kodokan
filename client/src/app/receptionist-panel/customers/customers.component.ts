import { Component, OnInit, ViewChild } from '@angular/core';
import { Customer } from '../../data/customer/Customer'
import { CustomerService } from '../../service/customer.service'
import { IgxDialogActionsDirective } from 'igniteui-angular/lib/dialog/dialog.directives'
import { Router } from '@angular/router';
import { filterStates } from 'src/app/data/filter/filter-states';
import { IgxGridComponent } from 'igniteui-angular';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  customerList: Customer[] // błąd 
  customer_id:number
  deletedCustomer: Customer
  searchBox: string
  public caseSensitive = false;
  public exactMatch = false;

  public filter_states: filterStates[];
  public selectedFilters: number[]

  @ViewChild('grid',{static:true}) public grid: IgxGridComponent
  @ViewChild("dialog") dialog; // do dialog.open()/dialog.close() 

  constructor(private CustomerService: CustomerService, private router: Router) { 
    this.filter_states=[
      {id: 1,name:"imię"},
      {id: 2,name:"nazwisko"},
      {id: 3,name:"dyscyplina"},
      {id: 4,name:"numer telefonu"},
      {id: 5,name:"numer karty"}
    ]
  }
  
  ngOnInit(): void {
    

    this.CustomerService.getCustomers().subscribe(
      data => this.customerList = data,
      error => this.errorData(),
    );
  }

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

  

}
