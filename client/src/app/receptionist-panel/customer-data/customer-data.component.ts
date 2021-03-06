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

  client: Client = {
   
    addressLine: "Wolf Street 23",
    cardId: "123",
    city: "Arizona",
    email: "czak.noris@gmail.com",
    firstName: "Czak",
    gender: Gender.Male,
    id: 0,
    identityNumber: "333224411",
    image: "assets/ruda.jpg",
    lastName: "Noris",
    mainDiscipline: "Boks",
    phone: "66677788",
    postalCode: "00-233",
    voivodeship: "Ślunskie",
    password: "abc"
  };
  

  constructor(private clientService: ClientService, private router: Router, private urlParam: ActivatedRoute) { }

  ngOnInit(): void {
    //this.getClient(2) // <--- Na potrzeby testów
    this.getClient(parseInt(this.urlParam.snapshot.paramMap.get('id'))) // <--- ID klienta z adresu URL
    //this.router.navigate(["/receptionist-panel/customer-data", {card: this.client.identityNumber}])
  }

  getClient(id: number) : void {
    this.clientService.getClient(id).toPromise().then(data => {this.client = data; this.client.image = "data:image/jpeg;base64," + this.client.image;
                  /*this.client.image = "assets/ruda.jpg"/*<---- na potrzeby testów */});
  }

  toEditData(id_): void {
    this.router.navigate(['/receptionist-panel/customer-edit-data', {id:id_}])
  }

  toAddPackage(id_): void {
    this.router.navigate(['/receptionist-panel/add-package-receptionist', {id:id_}])
  }

  toClientPackage(fName): void {
    this.router.navigate(['/receptionist-panel/passes', {fullname:fName}])
  }

  toFamily(id_): void {
    this.router.navigate(['/customer-family', {id:id_}])
  }

}
