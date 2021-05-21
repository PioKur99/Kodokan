import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/data/client';
@Component({
  selector: 'app-customer-data',
  templateUrl: './customer-data.component.html',
  styleUrls: ['./customer-data.component.scss']
})
export class CustomerDataComponent implements OnInit {

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

}
