import { Component, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { EntranceToAdd } from 'src/app/data/entrance/entrance-to-add';
import { Training } from 'src/app/data/training/training';
import { EntranceService } from 'src/app/services/entrance.service';
import { TrainingService } from 'src/app/services/training.service';

@Component({
  selector: 'app-entry-registration',
  templateUrl: './entry-registration.component.html',
  styleUrls: ['./entry-registration.component.scss']
})
export class EntryRegistrationComponent implements OnInit {

  trainingList: Training[]
  entranceToAdd: EntranceToAdd
  entranceId: number
  //__trainingList: Training[]

  trainingSub: Subscription


  @ViewChild("alertok") alertok;
  @ViewChild("alertnotok") alertnotok;

  constructor(private trainingService: TrainingService, private entranceService: EntranceService) { 
    // this.trainingList = [{id: 1,name: "MMA"},
    // {id: 2,name: "BOX"},
    // {id: 3,name: "KOX"},
    // {id: 4,name: "LOS"},
    // {id: 5,name: "RNG"}
    // ]
  }

  ngOnInit(): void {
    this.trainingSub=
      this.trainingService.getTrainingShedule().subscribe(
        x => this.trainingList=x,
        //TODO: zrobić coś z tym errorem
        //error =>
      )
  }

  entryRegistration(): void{
    this.entranceService.postEntrance(this.entranceToAdd).subscribe(
      x=> {
        this.entranceId=x 
        this.alertok.open()
      },
      error => this.alertnotok.open()
    )
  }
}
