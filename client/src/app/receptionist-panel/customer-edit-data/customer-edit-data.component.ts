import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer-edit-data',
  templateUrl: './customer-edit-data.component.html',
  styleUrls: ['./customer-edit-data.component.scss']
})
export class CustomerEditDataComponent implements OnInit {

  //todo: wyświetlanie odpowiednich dialogów

   dialogSelector: string = "";
   whichDialog: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  manageDialogs() {
    this.whichDialog ?  this.dialogSelector = "dialog1.open()" : this.dialogSelector = "dialog2.open()"
    return this.dialogSelector;
  }

}
