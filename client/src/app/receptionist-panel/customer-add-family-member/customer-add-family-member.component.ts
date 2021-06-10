import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/data/client';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-add-family-member',
  templateUrl: './customer-add-family-member.component.html',
  styleUrls: ['./customer-add-family-member.component.scss']
})
export class CustomerAddFamilyMemberComponent implements OnInit {

  client: Client = {
    name: "Czak",
    surname: "Noris",
    gender: "Men",
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

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.navigate(["/receptionist-panel/customer-add-family-member", {cardID: this.client.cardNumb}])
  }

}
