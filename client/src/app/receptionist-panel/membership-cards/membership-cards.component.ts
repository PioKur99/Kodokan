import { Component, OnInit, ViewChild } from '@angular/core';
import { IgxGridComponent } from 'igniteui-angular';

@Component({
  selector: 'app-membership-cards',
  templateUrl: './membership-cards.component.html',
  styleUrls: ['./membership-cards.component.scss']
})
export class MembershipCardsComponent implements OnInit {

  public searchNameAndSurname: ''
  public caseSensitive = false;
  public exactMatch = false;

  cardState: {id: string, name: string}[]=[];
  public selectedCardState: string[]
  
  @ViewChild('grid',{static:true}) public grid: IgxGridComponent
  constructor() { 
  }

  ngOnInit(): void {
    this.cardState=[{id:'1',name: 'Nieopłacona'},
    {id:'2',name: 'Opłacona'},
    {id:'3',name: 'Wydrukowana'},
    {id:'4',name: 'Do odbioru'},
    {id:'5',name: 'odebrana'}]
  }

}
