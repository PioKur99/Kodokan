import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Entrance } from 'src/app/data/entrance/entrance';
import { EntranceService } from 'src/app/services/entrance.service';

@Component({
  selector: 'app-entrance',
  templateUrl: './entrance.component.html',
  styleUrls: ['./entrance.component.scss']
})
export class EntranceComponent implements OnInit {

  entrancesList: Entrance[]

  @ViewChild("errorGetEntrances") errorGetEntrances;

  constructor(public entranceService: EntranceService) { }

  ngOnInit(): void {
    this.getEntrances()
  }

  getEntrances(){
    this.entranceService.getEntrances().subscribe(
      x=>{
        this.entrancesList=x
        console.log(x)
      },
      error=>{
        this.openErrorGetEntrances()
      }
    )
  }

  openErrorGetEntrances(){
    this.errorGetEntrances.open()
  }
}
