import { Component, OnInit, ViewChild } from '@angular/core';
import { IgxGridComponent } from 'igniteui-angular';
import { Observable, Subscription } from 'rxjs';
import { Card } from 'src/app/data/card/card';
import { CardState } from 'src/app/data/card/card-state';
import { CardStates } from 'src/app/data/card/card-states.enum';
import { MapState } from 'src/app/data/card/map-state';
import { Customer } from 'src/app/data/customer/customer';
import { CustomerAndCardState } from 'src/app/data/customer/customer-and-card-state';
import { CustomerFilter } from 'src/app/data/customer/customer-filter';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-membership-cards',
  templateUrl: './membership-cards.component.html',
  styleUrls: ['./membership-cards.component.scss']
})
export class MembershipCardsComponent implements OnInit {

  public searchNameAndSurname: ''
  public cardNumberToBeSet: string
  public caseSensitive = false;
  public exactMatch = false;
  public customerFilter: CustomerFilter

  cardStates: Card[];
  map = new MapState()
  public selectedCardState: number[]
  
  @ViewChild('grid',{static:true}) public grid: IgxGridComponent
  @ViewChild('cardNumberForm') cardNumberForm
  @ViewChild('errorGetCustomers') errorGetCustomers
  @ViewChild('errorGetCardState') errorGetCardState
  @ViewChild('errorChangeCardState') errorChangeCardState
  @ViewChild('errorAddCard') errorAddCard
  @ViewChild('errorForm') errorForm

  public customerList: Customer[]
  public customerSub: Subscription
  public cardStateList: CardState[]
  public cardStateSub: Subscription
  
  public customerAndCardStateList: CustomerAndCardState[] = new Array<CustomerAndCardState>()
  
  public customerObs: Observable<any>

  constructor(public customerService: CustomerService) { 
  }

  ngOnInit(): void {
    this.cardStates=[
    {id: CardStates.Nieoplacona, name: 'Nieopłacona'},
    {id: CardStates.Oplacona, name: 'Opłacona'},
    {id: CardStates.Wydrukowana, name: 'Wydrukowana'},
    {id: CardStates.Do_odbioru, name: 'Do odbioru'},
    {id: CardStates.Odebrana, name: 'Odebrana'}]    

    this.getCustomers()
    this.getCardsState()
  }

  //pobranie customerów
  getCustomers(){
    this.customerSub = this.customerService.getCustomers().subscribe(
      x => {
        this.customerList=x
        console.log("pobrano customerów")
        console.log(this.customerList[0].customerID)

        //przepisanie customerów
        this.customerList.forEach(
          y =>{
            this.customerAndCardStateList.push(new CustomerAndCardState(y,new CardState()))
          }
        )
        console.log("przepisano customerów")
        console.log(this.customerAndCardStateList[0].customer.customerID)
      },
      err=>{
        this.errorGetCustomers.open()
      }
    )
  }

  //pobranie cardstate dla wszystkich customerów
  getCardsState(){
    this.customerAndCardStateList.forEach(
      x => {
        this.cardStateSub = this.customerService.getCardState(x.customer.customerID).subscribe(
          y=>{
            x.cardState=y
            console.log("pobrano cardstates")
            console.log(y,x.cardState)
          },
          err=>{
            this.errorGetCardState.open()
          }
        )
        console.log(x,"consolelog x")
      }
    )
  }

  //sprawdzenie warunków i przygotowanie do zmiany stanu karty
  switchCardStatePrep(customer: CustomerAndCardState,updown: number){
    let param: string
    switch(updown){
      case 1:param="down" 
        break
      case 2:param="up"
        break
    }

    this.customerObs = new Observable(x=>{x.next({customer,param})})
    if(param==="up"&&customer.cardState.next==="READY"){
      this.cardNumberForm.open()
    }else{
      this.switchCardState()
    }
  }

  //zmiana stanu karty
  switchCardState(){
    this.customerObs.subscribe(x=>{
      this.customerService.switchCardState(x.customer.customer.customerID,x.param).subscribe(
        y=>{
          x.customer.cardState=y
          console.log(y)
        },
        err=>{
          this.errorChangeCardState.open()
        },
        ()=>{
          this.customerService.getCardState(x.customer.customer.customerID).subscribe(
            z=>{
              x.customer.cardState=z
            },
            error=>{
              this.errorGetCardState.open()
            }
          )
        }
      )
    })
  }

  //ustawienie numeru karty
  cardNumberFormFunc(){
    let y: number =+ this.cardNumberToBeSet
    let customer: CustomerAndCardState
    this.customerObs.subscribe(x=>{customer=x.customer})
    this.customerService.postCardId(customer.customer.customerID,y).subscribe(
      x=>{
        console.log(x)
      },
      err=>{
        this.errorAddCard.open()
      }
    )
    this.switchCardState()
    this.cardNumberForm.close()
  }
}
