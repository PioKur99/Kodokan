import { Component, OnInit } from '@angular/core';
import { Client, Gender } from 'src/app/data/client';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-add-family-member',
  templateUrl: './customer-add-family-member.component.html',
  styleUrls: ['./customer-add-family-member.component.scss']
})
export class CustomerAddFamilyMemberComponent implements OnInit {

  client: Client = {
    id: 1,
    image: "",
    firstName: "Czak",
    lastName: "Noris",
    gender: Gender.Male,
    identityNumber: "8890991812",
    addressLine: "Wolf Street 23",
    city: "Arizona",
    postalCode: "00-233",
    voivodeship: "Ślunskie",
    cardId: "333224411",
    email: "czak.noris@gmail.com",
    mainDiscipline: "Boks",
    phone: "66677788"
  };

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.navigate(["/receptionist-panel/customer-add-family-member", {cardID: this.client.cardId}])
  }

}
