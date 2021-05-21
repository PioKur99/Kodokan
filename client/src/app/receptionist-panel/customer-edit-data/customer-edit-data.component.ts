import { Component, OnInit, ViewChild } from '@angular/core';
import { Client } from 'src/app/data/client';

@Component({
  selector: 'app-customer-edit-data',
  templateUrl: './customer-edit-data.component.html',
  styleUrls: ['./customer-edit-data.component.scss']
})
export class CustomerEditDataComponent implements OnInit {

  //todo: wyświetlanie odpowiednich dialogów

   editWentGood: boolean = false;
   @ViewChild("dialog1") dialogSuccess;
   @ViewChild("dialog2") dialogFailure;

   client: Client = {
    name: "Czak",
    surname: "Noris",
    gender: "Siusiak",
    PESEL: "8890991812",
    streetNumb: "Wolf Street 23",
    apartmentNumb: "18",
    city: "Arizona",
    postCode: "00-233",
    voivodeship: "Ślunskie",
    cardNumb: "333224411",
    mail: "czak.noris@gmail.com",
    dyscypline: "Boks",
    phoneNumb: "66677788"

  };

  constructor() { }

  ngOnInit(): void {
  }

  manageDialogs() {
    if(this.editWentGood == true) {
      this.dialogSuccess.open();
    }
    else {
      this.dialogFailure.open();
    }
  }

}
