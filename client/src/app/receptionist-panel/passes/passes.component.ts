import { Component, OnInit } from '@angular/core';
import { PackageDetails } from 'src/app/data/package/package-details';
import { PackageService } from 'src/app/services/package.service';
import * as config from '../../../assets/config.json'

@Component({
  selector: 'app-passes',
  templateUrl: './passes.component.html',
  styleUrls: ['./passes.component.scss']
})
export class PassesComponent implements OnInit {

  actionUrl=config.address + "customer/"
  packageDetails: PackageDetails
  packageId: Number = 1
  constructor(public packageService: PackageService) { }

  ngOnInit(): void {
    this.getPackageDetails(1)
  }

  getPackageDetails(packageId: number){
    this.packageService.getPackageDetails(packageId).subscribe(
      x=>{this.packageDetails=x
        //TODO: wyłapać error
      }
    )

  }
  showEntrances(){
    window.location.href="receptionist-panel/entrance/"+ this.packageId.toString()
  }
}
