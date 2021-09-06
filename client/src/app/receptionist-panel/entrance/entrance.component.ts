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
  checkboxes: boolean[];
  deletedEntrances: number

  @ViewChild("dialog") dialog;
  @ViewChild("errorGetEntrances") errorGetEntrances;
  @ViewChild("errorDeleteEntrance") errorDeleteEntrance;

  constructor(public entranceService: EntranceService) { }

  ngOnInit(): void {
    this.entranceService.getEntrances().subscribe(
      x=>{
        this.entrancesList=x
        console.log(x)
      },
      error=>{
        this.openErrorGetEntrances()
      },
      ()=>{
        this.checkboxes = new Array(this.entrancesList.length);
        this.checkboxes.fill(false);
      }
    )
  }
  //zmienia zaznaczenia w tablicy checkboxes
  toggleSelection(event, i) {
    this.checkboxes[i] = event.checked;
    console.log(this.checkboxes)
  }

  deleteEntrances(){
    for(let i=0;i< this.checkboxes.length;++i){
      if(this.checkboxes[i]){
        this.entranceService.deleteEntrance(this.entrancesList[i].entrance_id).subscribe(
          x=>{
            this.deletedEntrances=x
            console.log("usunięto " + x)
          },
          error=>{
            this.openErrorDeleteEntrance()
          }
        )
      }
    }
    this.dialog.close()
  }

  openErrorGetEntrances(){
    this.errorGetEntrances.open()
  }

  openErrorDeleteEntrance(){
    this.errorDeleteEntrance.open()
  }

  
}
