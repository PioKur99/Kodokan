import { CardState } from "../card/card-state";
import { Customer } from "./customer";

export class CustomerAndCardState {
    customer: Customer
    cardState: CardState
    constructor(customer?: Customer,
        cardState?: CardState){
            if(!customer && !cardState){
                this.customer=new Customer()
                this.cardState=new CardState()
            }else{
                this.customer=customer
                this.cardState=cardState
            }
    }
}