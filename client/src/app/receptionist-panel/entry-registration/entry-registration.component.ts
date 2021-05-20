import { Component, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { Training } from 'src/app/data/training/training';

@Component({
  selector: 'app-entry-registration',
  templateUrl: './entry-registration.component.html',
  styleUrls: ['./entry-registration.component.scss']
})
export class EntryRegistrationComponent implements OnInit {

  trainingList: Training[]
  //__trainingList: Training[]
  //TODO: dodać User do data
  //user: User
  userSub: Subscription
  trainingSub: Subscription

  @ViewChild("alertok") alertok;
  @ViewChild("alertnotok") alertnotok;

  constructor() { 
    // this.trainingList = [{id: 1,name: "MMA"},
    // {id: 2,name: "BOX"},
    // {id: 3,name: "KOX"},
    // {id: 4,name: "LOS"},
    // {id: 5,name: "RNG"}
    // ]
  }

  ngOnInit(): void {
    // TODO: user Service
    // this.userSub =
    //   this.userService.getAuthenticationEvent().subscribe(user => {
    //       this.user = user;
    //       if (this.user != null)
    //         this.trainingSub = this.trainingService.getTrainingShedule(user.id).subscribe(trainings => this.trainingList = trainings);
    //     }
    //   );
  }

  entryRegistration(): void{
    this.alertnotok.open()

  }
}
