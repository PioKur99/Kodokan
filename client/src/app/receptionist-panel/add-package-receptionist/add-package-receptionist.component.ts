import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Package } from 'src/app/data/package';
import { AddPackageReceptionistService } from 'src/app/services/add-package-receptionist.service';
import { PackageService } from 'src/app/services/package.service';
import { PackageType } from 'src/app/data/package/package-type';
@Component({
  selector: 'app-add-package-receptionist',
  templateUrl: './add-package-receptionist.component.html',
  styleUrls: ['./add-package-receptionist.component.scss']
})
export class AddPackageReceptionistComponent implements OnInit {

  
  @ViewChild ("errordialog") errordialog;
  @ViewChild ("paydialog") paydialog;
  @ViewChild ("dialog") dialog;

  
  packages: Array<PackageType>;

  package_id: String;
  name_surname: String;
  client_id: String;
  errorMessage: String;

  constructor(private route: ActivatedRoute, private addPackageService: AddPackageReceptionistService, private router: Router, private packageService: PackageService) { 
  
  }

  ngOnInit(): void {
    this.client_id = this.route.snapshot.paramMap.get("id");
    this.findNameById();
    this.getPackages();
  }

  findNameById():void{
    this.addPackageService.findCustomerById(this.client_id).subscribe(
      resp => {
        this.name_surname = resp.firstName+ " " + resp.lastName;
      },
      err => {
      }
    );
  }
  
  getPackages():void{
    this.packageService.getPackageType(false).subscribe(
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
        this.router.navigate(["/receptionist-panel/passes/"+this.package_id]);
      },
      err => {
        this.dialog.close();
        this.errorMessage = err.error.message;
        this.errordialog.open();
      }
    );
  }
  
  payForPackage(chosen_type_id: String):void{
    this.addPackageService.createPackage(this.client_id, chosen_type_id).subscribe(
      resp => {
        this.package_id = resp;
        this.addPackageService.payForPackage(this.package_id).subscribe(
          resp => {
            this.router.navigate(["/receptionist-panel/passes/"+this.package_id]);
          },
          err => {
            this.paydialog.close();
            this.dialog.close();
            this.errorMessage = err.error.message;
            this.errordialog.open();
          }
        );
      },
      err => {
        this.dialog.close();
        this.errorMessage = err.error.message;
        console.log(err.error)
        this.errordialog.open();
      }
    );

  }
  
}
