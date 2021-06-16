import { Component, OnInit } from '@angular/core';
import { Client, Gender } from 'src/app/data/client';
import { ClientService } from 'src/app/services/client.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-customer-data',
  templateUrl: './customer-data.component.html',
  styleUrls: ['./customer-data.component.scss']
})

export class CustomerDataComponent implements OnInit {

  PESEL: string = "8890991812"
  apartmentNumb: string = "18"
  client: Client = {
    /*name: "Czak",
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
    phoneNumb: "66677788",*/

    addressLine: "Wolf Street 23",
    city: "Arizona",
    email: "czak.noris@gmail.com",
    firstName: "Czak",
    gender: Gender.Male,
    id: 0,
    identityNumber: "333224411",
    image: "",
    lastName: "Noris",
    mainDiscipline: "Boks",
    phone: "66677788",
    postalCode: "00-233",
    voivodeship: "Ślunskie",

  };
  

  constructor(private clientService: ClientService, private router: Router, private urlParam: ActivatedRoute) { }

  ngOnInit(): void {
    this.getClient(0) // <--- Na potrzeby testów
    //this.getClient(this.urlParam.snapshot.paramMap.get('id')) <--- ID klienta z adresu URL
    this.router.navigate(["/receptionist-panel/customer-data", {cardID: this.client.identityNumber}])
  }

  getClient(id: number) : void {
    this.clientService.getClient(id).toPromise().then(data => {this.client = data});
  }

}
