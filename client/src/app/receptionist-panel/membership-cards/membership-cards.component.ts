import { Component, OnInit, ViewChild } from '@angular/core';
import { IgxGridComponent } from 'igniteui-angular';
import { Subscription } from 'rxjs';
import { Card } from 'src/app/data/card/card';
import { CardState } from 'src/app/data/card/card-state';
import { Customer } from 'src/app/data/customer/customer';
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

  cardState: Card[];
  public selectedCardState: string[]
  
  @ViewChild('grid',{static:true}) public grid: IgxGridComponent
  
  public customerList: Customer[]
  public customerSub: Subscription
  public cardStateList: CardState[]
  public cardStateSub: Subscription

  constructor(public customerService: CustomerService) { 
  }

  ngOnInit(): void {
    this.cardState=[{id:1,name: 'Nieopłacona'},
    {id:2,name: 'Opłacona'},
    {id:3,name: 'Wydrukowana'},
    {id:4,name: 'Do odbioru'},
    {id:5,name: 'odebrana'}]

    this.customerSub = this.customerService.getCustomers().subscribe(
      x => this.customerList=x
      //TODO: obsłużyć error
    )

    let i:any
    for(i in this.customerList){
      this.cardStateSub = this.customerService.getCardState(i.customer_id).subscribe(
        x=> this.cardStateList.push(x)
        //TODO: obsłużyć error
      )
    }
  }
}
