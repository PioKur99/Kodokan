export class CardState {
    previous: number
    actual: number
    next: number

    constructor(previous?: number, actual?: number, next?: number){
        if(!previous && !actual && !next){
           
        }else{
            this.previous=previous
            this.actual=actual
            this.next=next
        }
    }
}
