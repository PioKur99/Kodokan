import { animate, transition, style, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';

@Component({
  selector: 'app-customer-panel',
  templateUrl: './customer-panel.component.html',
  styleUrls: ['./customer-panel.component.scss'],
  animations: [
    trigger('flyInOut', [
      transition('void => *', [
        style({transform: 'translateX(100%)'}),
        animate('0.3s')
      ]),
      transition('* => void', [
        animate('0.3s', style ({transform: 'translateX(100%)'}))
      ])
    ])
  ]
})
export class CustomerPanelComponent implements OnInit {

  divVisible: boolean = false;

  optionsRunner: AnimationOptions = {
    path: 'assets/adidas-runner.json'
  }


  constructor() { }

  ngOnInit(): void {
  }

  showDiv(){
    this.divVisible = true;
  }

  hideDiv(){
    this.divVisible = false;
  }

}
