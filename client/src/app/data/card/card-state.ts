
export class CardState {
    previous: string
    actual: string
    next: string

    constructor(previous?: string, actual?: string, next?: string){
        if(!previous && !actual && !next){
           
        }else{
            this.previous=previous
            this.actual=actual
            this.next=next
        }
    }
}
