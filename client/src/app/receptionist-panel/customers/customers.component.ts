import { Component, OnInit, ViewChild } from '@angular/core';
import { Customer } from '../../data/customer/Customer'
import { CustomerService } from '../../service/customer.service'
import { IgxDialogActionsDirective } from 'igniteui-angular/lib/dialog/dialog.directives'
import { Router } from '@angular/router';
import { filterStates } from 'src/app/data/filter/filter-states';
import { IgxGridComponent } from 'igniteui-angular';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  customerList: Customer[]
  deletedCustomer: Customer
  searchBox: string

  //zmienne do combo 
  public caseSensitive = false;
  public exactMatch = false;
  public filter_states: filterStates[];
  public selectedFilters: number[]
  @ViewChild('grid',{static:true}) public grid: IgxGridComponent
  
  @ViewChild("dialogDelete") dialogDelete; 
  @ViewChild("errorGetCustomers") errorGetCustomers; 

  public customerObs: Observable<any>

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
    this.getCustomers()
  }

  getCustomers(){
    this.CustomerService.getCustomers().subscribe(
      data => this.customerList = data,
      error => this.openErrorGetCustomers(),
    );
  }

  deleteCustomerPrep(customer: Customer): void {
      this.customerObs = new Observable(x=>{x.next(customer)})
      this.dialogDelete.open()
  }

  deleteCustomer(): void {
    this.customerObs.subscribe(x=>{
      this.CustomerService.deleteCustomer(x.customer_id).subscribe(
        y => {
          this.deletedCustomer=x
          console.log(y);
        });
    })
    this.dialogDelete.close();
    this.getCustomers();
  }

  openErrorGetCustomers() {
    this.errorGetCustomers.open();
  }

  errorDataLink(): void {
    this.router.navigate(["/receptionist-panel"])
  }

}
