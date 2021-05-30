import { Component, OnInit, ViewChild } from '@angular/core';
import { IgxGridComponent } from 'igniteui-angular';
import { Subscription } from 'rxjs';
import { Card } from 'src/app/data/card/card';
import { CardState } from 'src/app/data/card/card-state';
import { Customer } from 'src/app/data/customer/customer';
import { CustomerAndCardState } from 'src/app/data/customer/customer-and-card-state';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-membership-cards',
  templateUrl: './membership-cards.component.html',
  styleUrls: ['./membership-cards.component.scss']
})
export class MembershipCardsComponent implements OnInit {

  public searchNameAndSurname: ''
  public caseSensitive = false;
  public exactMatch = false;

  cardStates: Card[];
  public selectedCardState: string[]
  
  @ViewChild('grid',{static:true}) public grid: IgxGridComponent
  
  public customerList: Customer[]
  public customerSub: Subscription
  public cardStateList: CardState[]
  public cardStateSub: Subscription
  
  public customerAndCardStateList: CustomerAndCardState[] = new Array<CustomerAndCardState>()
  
  constructor(public customerService: CustomerService) { 
  }

  ngOnInit(): void {
    this.cardStates=[
    {id: 1, name: 'Nieopłacona'},
    {id: 2, name: 'Opłacona'},
    {id: 3, name: 'Wydrukowana'},
    {id: 4, name: 'Do odbioru'},
    {id: 5, name: 'Odebrana'}]

    this.customerList=[
    {customer_id: 1, first_name: 'Ali', last_name: 'Muhammad'},
    {customer_id: 2, first_name: 'Bali', last_name: 'Chill'}]

    //pobranie customerów
    this.customerSub = this.customerService.getCustomers().subscribe(
      x => this.customerList=x
      //TODO: obsłużyć error
    )

    //przepisanie customerów
    this.customerList.forEach(
      x =>{
        this.customerAndCardStateList.push(new CustomerAndCardState(x,new CardState()))
      }
    )

    let k = new CardState(null,2,3)
    this.customerAndCardStateList.forEach(x => x.cardState=k)
      

    this.customerAndCardStateList.forEach(
      x => {
        this.cardStateSub = this.customerService.getCardState(x.customer.customer_id).subscribe(
          y=>{
            x.cardState=y
          }
          //obsłużyć error
        )
      }
    )
  }
}
