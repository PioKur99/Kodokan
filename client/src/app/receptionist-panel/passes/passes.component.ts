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
  constructor(public packageService: PackageService) { }

  ngOnInit(): void {
    this.getPackageDetails(1)
  }

  getPackageDetails(package_id: number){
    this.packageService.getPackageDetails(package_id).subscribe(
      x=>{this.packageDetails=x
        //TODO: wyłapać error
      }
    )

  }
}
