import { Component, OnInit, ViewChild } from '@angular/core';
import { Client } from 'src/app/data/client';
import { Router } from '@angular/router';
import { FamilyMember, Relationship } from 'src/app/data/family-member';
import { FamilyService } from 'src/app/services/family.service';

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

  testRodzic: FamilyMember = {firstName: "Amanda", lastName: "Noris", identificationNumber: "2323232",relationship: Relationship.rodzic}
  testMalzenstwo: FamilyMember = {firstName: "Nicole", lastName: "Noris", identificationNumber: "2323233",relationship: Relationship.malzenstwo}
  testRodzenstwo1: FamilyMember = {firstName: "Sam", lastName: "Noris", identificationNumber: "2323234",relationship: Relationship.rodzenstwo}
  testRodzenstwo2: FamilyMember = {firstName: "Steve", lastName: "Noris", identificationNumber: "2323235",relationship: Relationship.rodzenstwo}
  familyArr: FamilyMember[] = [this.testRodzic, this.testMalzenstwo, this.testRodzenstwo1, this.testRodzenstwo2];

  showDeleteButton: Boolean = false;

  fullName: String = this.client.name + " " + this.client.surname;

  @ViewChild("dialog1") dialogDelete;
  @ViewChild("dialog2") dialogSuccess;
  @ViewChild("dialog3") dialogFail;

  constructor(private router: Router, private familyService: FamilyService) { }

  ngOnInit(): void {
    this.router.navigate(["/receptionist-panel/customer-family", {cardID: this.client.cardNumb}])
    this.getFamily()
  }

  toggleDeleteButton(): void {
    this.showDeleteButton = !this.showDeleteButton;
  }

  getFamily() : void {
    this.familyService.getFamilyMembers(this.client.cardNumb).subscribe((data: FamilyMember[]) => this.familyArr = data)
  }

  deleteFamilyMember(member: FamilyMember){
    this.dialogDelete.close();
    const index = this.familyArr.indexOf(member);
    this.familyArr.splice(index, 1);
    this.familyService.deleteFamilyMember(member.identificationNumber).subscribe(
      data =>{this.dialogSuccess.open()},
      error => {this.dialogFail.open()}
    )
  }

}
