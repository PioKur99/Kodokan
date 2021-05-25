import { Component, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { EntranceToAdd } from 'src/app/data/entrance/entrance-to-add';
import { Package } from 'src/app/data/package/package';
import { PackageType } from 'src/app/data/package/package-type';
import { Training } from 'src/app/data/training/training';
import { EntranceService } from 'src/app/services/entrance.service';
import { PackageService } from 'src/app/services/package.service';
import { TrainingService } from 'src/app/services/training.service';

@Component({
  selector: 'app-entry-registration',
  templateUrl: './entry-registration.component.html',
  styleUrls: ['./entry-registration.component.scss']
})
export class EntryRegistrationComponent implements OnInit {

  trainingList: Training[]
  packageTypeList: PackageType[]
  pass: Package

  entranceToAdd: EntranceToAdd
  entranceId: number
  packageId: number

  trainingSub: Subscription
  packageTypeSub: Subscription
  packageSub: Subscription

  @ViewChild("alertok") alertok;
  @ViewChild("alertnotok") alertnotok;

  constructor(private trainingService: TrainingService, private packageService: PackageService, private entranceService: EntranceService) { 
    this.trainingList = [{shedule_id: 1,name: "MMA"},
    {shedule_id: 2,name: "BOX"},
    {shedule_id: 3,name: "KOX"},
    {shedule_id: 4,name: "LOS"},
    {shedule_id: 5,name: "RNG"},
    ]

    this.packageTypeList = [{package_type_id: 1, name: "Jednorazówka"},
    {package_type_id: 2, name: "Multisport"}
    ]

    this.pass={type_id:1, customer_id:2}
  }

  ngOnInit(): void {
    this.trainingSub=
      this.trainingService.getTrainingShedule().subscribe(
        x => this.trainingList=x,
        //TODO: zrobić coś z tym errorem
        //error =>
      )
    this.packageTypeSub=
      this.packageService.getPackageType().subscribe(
        x => this.packageTypeList=x,
      )
  }

  entryRegistration(): void{
    // this.entranceService.postEntrance(this.entranceToAdd).subscribe(
    //   x=> {
    //     this.entranceId=x 
    //     this.alertok.open()
    //   },
    //   error => this.alertnotok.open()
    // )
    this.alertnotok.open()
  }

  entryRegistration2(): void{
    this.packageSub=
      this.packageService.postAddPackage(this.pass).subscribe(
        x => this.packageId=x,
      )
    //addPass
    //PassPaid
  }
}
