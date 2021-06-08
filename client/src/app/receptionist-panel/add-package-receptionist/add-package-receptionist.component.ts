import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Package } from 'src/app/data/package';
import { AddPackageReceptionistService } from 'src/app/services/add-package-receptionist.service';

@Component({
  selector: 'app-add-package-receptionist',
  templateUrl: './add-package-receptionist.component.html',
  styleUrls: ['./add-package-receptionist.component.scss']
})
export class AddPackageReceptionistComponent implements OnInit {

  package_1: Package =  {
    title: 'KARNET JEDNORAZOWY',
    subtitle: 'JEDNORAZOWY',
    price: 20,
    description: 'Karnet ważny w dniu zakupu, upoważnia do jednorazowego wejścia.',
    type_id: ''

  }
  package_2: Package =  {
    title: 'KARNET MIESIĘCZNY 4 WEJŚCIA ',
    subtitle: '4 WEJŚCIA',
    price: 70,
    description: 'Karnet upoważnia do 4 wejść w ciągu jednego miesiąca od zakupu',
    type_id: ''
  }
  package_3: Package =  {
    title: 'KARNET MIESIĘCZNY',
    subtitle: '8 WEJŚĆ',
    price: 120,
    description: 'Odpowiedni opis karnetu.',
    type_id: ''
  }
  package_4: Package =  {
    title: 'KARNET OPEN',
    subtitle: 'TYGODNIOWY',
    price: 50,
    description: 'Odpowiedni opis karnetu.',
    type_id: ''
  }
  package_5: Package =  {
    title: 'KARNET DZIECIĘCY',
    subtitle: '...',
    price: 100,
    description: 'Odpowiedni opis karnetu.',
    type_id: ''
  }
  //packages: Array<Package> = [this.package_1, this.package_2, this.package_3, this.package_4, this.package_5, this.package_2, this.package_3, this.package_4,];

  @ViewChild ("errordialog") errordialog;
  @ViewChild ("paydialog") paydialog;
  @ViewChild ("dialog") dialog;

  packages: Array<Package>;

  package_id: String;
  name_surname: String;
  client_id: String;

  findNameById():void{
    this.addPackageService.findCustomerById(this.client_id).subscribe(
      resp => {
        this.name_surname = resp.firstName+resp.lastName;
      },
      err => {
      }
    );
  }
  
  getPackages():void{
    this.addPackageService.getPackages().subscribe(
      resp => {
        this.packages = resp;
      },
      err => {
        this.errordialog.open();
      }
    );
  }

  createPackage(chosen_type_id: String):void{
      this.addPackageService.createPackage(this.client_id, chosen_type_id).subscribe(
      resp => {
        this.package_id = resp;
        this.router.navigate(["/passes"+this.client_id]);
      },
      err => {
        this.dialog.close();
        this.errordialog.open();
      }
    );
  }
  payForPackage(chosen_type_id: String):void{
    this.createPackage(chosen_type_id);
    if(this.package_id){
      this.addPackageService.payForPackage(this.package_id).subscribe(
        resp => {
          this.router.navigate(["/passes"+ this.client_id]);
        },
        err => {
          this.paydialog.close();
          this.dialog.close();
          this.errordialog.open();
        }
      );
    }

  }
  constructor(private route: ActivatedRoute, private addPackageService: AddPackageReceptionistService, private router: Router) { 
  }

  ngOnInit(): void {
    this.client_id = this.route.snapshot.paramMap.get("id");
    this.findNameById();
    this.getPackages();
  }

  
}
