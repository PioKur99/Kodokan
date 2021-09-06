import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PackageService } from 'src/app/services/package.service';
import { PackageDetails} from '../../data/package/package-details'

@Component({
  selector: 'app-pass-details',
  templateUrl: './pass-details.component.html',
  styleUrls: ['./pass-details.component.scss']
})


export class PassDetailsComponent implements OnInit {
  
  packageId: number
  packageDetails: PackageDetails

  constructor(private route: ActivatedRoute,private packageService: PackageService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap
    const packageIdFromRoute = Number(routeParams.get('packageId'))
    this.packageId=packageIdFromRoute
    this.packageService.getPackageDetails(packageIdFromRoute).subscribe(
      x=>{
        this.packageDetails=x
        console.log(x)
      },
      err=>{},
      ()=> {
        this.packageDetails.purchase_date = new Date(this.packageDetails.purchaseDate)
        this.packageDetails.end_date = new Date(this.packageDetails.endDate)
      }
    )
  }

  showEntrances(){
    window.location.href="receptionist-panel/entrance/"+ this.packageId.toString()
  }

}



