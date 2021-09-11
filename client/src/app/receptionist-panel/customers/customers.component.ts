import { Component, OnInit, ViewChild } from '@angular/core';
import { Customer } from '../../data/customer/Customer'
import { CustomerService } from '../../services/customer.service'
import { IgxDialogActionsDirective } from 'igniteui-angular/lib/dialog/dialog.directives'
import { Router } from '@angular/router';
import { filterStates } from 'src/app/data/filter/filter-states';
import { IComboSelectionChangeEventArgs, IgxGridComponent } from 'igniteui-angular';
import { Observable } from 'rxjs';
import { SearchCustomer } from 'src/app/data/customer/search-customer';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  customerList: Customer[]
  deletedCustomer: Customer
  searchBox: string
  searchCustomer = new SearchCustomer()

  //zmienne do combo 
  public caseSensitive = false;
  public exactMatch = false;
  public filter_states: filterStates[];
  public selectedFilters: number[]
  @ViewChild('grid',{static:true}) public grid: IgxGridComponent
  
  @ViewChild("dialogDelete") dialogDelete; 
  @ViewChild("errorGetCustomers") errorGetCustomers; 
  @ViewChild("errorDeleteCustomer") errorDeleteCustomer
  @ViewChild("errorFilter") errorFilter

  public customerObs: Observable<any>

  constructor(private CustomerService: CustomerService, private router: Router) { 
    this.filter_states=[
      {id: 1,name:"imię"},
      {id: 2,name:"nazwisko"},
      {id: 3,name:"stan karty"},
      {id: 4,name:"numer telefonu"},
      {id: 5,name:"numer karty"}
    ]
  }
  
  ngOnInit(): void {
    this.getCustomers()
  }

  getCustomers(){
    this.CustomerService.getCustomers(this.searchCustomer).subscribe(
      data => {
        this.customerList = data
        console.log(data)
      },
      error => this.errorGetCustomers.open()
    );
  }

  deleteCustomerPrep(customer: Customer): void {
    console.log(customer)
      this.customerObs = new Observable(x=>{x.next(customer)})
      this.dialogDelete.open()
  }

  deleteCustomer(): void {
    this.customerObs.subscribe(
      x=>{
        this.CustomerService.deleteCustomer(x.customerID).subscribe(
          y => {
            this.deletedCustomer=x
            console.log(y);
          },
          error=>{
            this.errorDeleteCustomer.open()
          },
          ()=>{
            this.getCustomers();
          }
        );
      }
    )
    this.dialogDelete.close();
  }

  searchCustomers(event){
    console.log("pressenter: ",event.target.value)
    this.searchCustomer=new SearchCustomer()
    if(this.selectedFilters){
      this.selectedFilters.forEach(
        x=>{
          console.log(x)
          switch(x){
            case 1: {
              this.searchCustomer.firstName=event.target.value
              break
            }
            case 2: {
              this.searchCustomer.lastName=event.target.value
              break
            }
            case 3: {
              this.searchCustomer.cardState=event.target.value
              break
            }
            case 4: {
              this.searchCustomer.phone=event.target.value
              break
            }
            case 5: {
              this.searchCustomer.cardID=event.target.value
              break
            }
          }
        }
      )
    }else{
      this.errorFilter.open()
    }
    this.getCustomers()
    
  }

  singleSelection(event: IComboSelectionChangeEventArgs) {
    if (event.added.length) {
      event.newSelection = event.added;
    } 
  }

  errorDataLink(): void {
    this.router.navigate(["/receptionist-panel"])
  }

}
