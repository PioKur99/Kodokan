import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Package } from 'src/app/data/package';

@Component({
  selector: 'app-add-package-receptionist',
  templateUrl: './add-package-receptionist.component.html',
  styleUrls: ['./add-package-receptionist.component.scss']
})
export class AddPackageReceptionistComponent implements OnInit {

  package_1: Package =  {
    title: 'KARNET',
    subtitle: 'JEDNORAZOWY',
    price: 20,
    description: 'Karnet ważny w dniu zakupu, upoważnia do jednorazowego wejścia.'
  }
  package_2: Package =  {
    title: 'KARNET MIESIĘCZNY ',
    subtitle: '4 WEJŚCIA',
    price: 70,
    description: 'Karnet upoważnia do 4 wejść w ciągu jednego miesiąca od zakupu'
  }
  package_3: Package =  {
    title: 'KARNET MIESIĘCZNY',
    subtitle: '8 WEJŚĆ',
    price: 120,
    description: 'Odpowiedni opis karnetu.'
  }
  package_4: Package =  {
    title: 'KARNET OPEN',
    subtitle: 'TYGODNIOWY',
    price: 50,
    description: 'Odpowiedni opis karnetu.'
  }
  package_5: Package =  {
    title: 'KARNET DZIECIĘCY',
    subtitle: '...',
    price: 100,
    description: 'Odpowiedni opis karnetu.'
  }
  packages: Array<Package> = [this.package_1, this.package_2, this.package_3, this.package_4, this.package_5, this.package_2, this.package_3, this.package_4,];

  name_surname: String;

  constructor(private route: ActivatedRoute) { 
  }

  ngOnInit(): void {
    this.name_surname = this.route.snapshot.paramMap.get("name");
  }
  
}
