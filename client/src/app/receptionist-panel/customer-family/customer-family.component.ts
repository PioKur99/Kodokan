import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/data/client';
import { Router } from '@angular/router';
import { FamilyMember } from 'src/app/data/family-member';

@Component({
  selector: 'app-customer-family',
  templateUrl: './customer-family.component.html',
  styleUrls: ['./customer-family.component.scss']
})
export class CustomerFamilyComponent implements OnInit {

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

  familyArr: FamilyMember[] = [
    {"firstName": "Amanda", "lastName": "Noris", "identificationNumber": "2323232", "relationship": "Rodzic"},
    {"firstName": "Nicole", "lastName": "Noris", "identificationNumber": "2355532", "relationship": "Małżeństwo"}
  ];

  fullName: String = this.client.name + " " + this.client.surname;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.navigate(["/receptionist-panel/customer-family", {cardID: this.client.cardNumb}])
  }

}
