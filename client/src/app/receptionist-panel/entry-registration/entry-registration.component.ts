import { Component, OnInit } from '@angular/core';
import { Training } from 'src/app/data/training/training';

@Component({
  selector: 'app-entry-registration',
  templateUrl: './entry-registration.component.html',
  styleUrls: ['./entry-registration.component.scss']
})
export class EntryRegistrationComponent implements OnInit {

  trainingList: Training[]
  //__trainingList: Training[]

  constructor() { 
    this.trainingList = [{id: 1,name: "MMA"},
    {id: 2,name: "BOX"},
    {id: 3,name: "KOX"},
    {id: 4,name: "LOS"},
    {id: 5,name: "RNG"}
    ]
  }

  ngOnInit(): void {

  }

}
