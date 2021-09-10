import { Component, OnInit, ViewChild } from '@angular/core';
import { Passes } from 'src/app/data/passes';
import { PassesService } from 'src/app/services/passes.service';

@Component({
  selector: 'app-passes',
  templateUrl: './passes.component.html',
  styleUrls: ['./passes.component.scss']
})
export class PassesComponent implements OnInit {
  
  //TODO: zrobić wyszukiwanie karnetów
  passesList: Passes[]
  checkboxes: boolean[]
  deletedPasses: number

  @ViewChild("dialog") dialog;
  @ViewChild("errorGetPasses") errorGetPasses;
  @ViewChild("errorDeletePasses") errorDeletePasses;

  constructor(public passesService: PassesService) { }

  ngOnInit(): void {
    this.getPasses()
    
  }
  //pobiera listę karnetów
  getPasses(){
    this.passesService.getPasses().subscribe(
      x=>{
        this.passesList=x
        console.log(x)
      },
      error=>{
        this.openErrorGetPasses()
      },
      () =>{
        this.passesList.forEach(i => {
          i.end_date= new Date(i.endDate)
        });
        this.checkboxes = new Array(this.passesList.length);
        this.checkboxes.fill(false);
      }
    )
  }
  //zmienia zaznaczenia w tablicy checkboxes
  toggleSelection(event, i) {
    this.checkboxes[i] = event.checked;
    console.log(this.checkboxes)
  }
  //usuwa zaznaczone karnety
  deletePasses(){
    for(let i=0;i< this.checkboxes.length;++i){
      if(this.checkboxes[i]){
        this.passesService.deletePasses(this.passesList[i].packageId).subscribe(
          x=>{
            this.deletedPasses=x
            console.log("usunięto " + x)
          },
          err=>{
            console.log(err.message)
            this.openErrorDeletePasses()
          },
          ()=>{
            this.getPasses()
          }
        )
      }
    }
    this.dialog.close()
  }

  openErrorGetPasses(){
    this.errorGetPasses.open()
  }

  openErrorDeletePasses(){
    this.errorDeletePasses.open()
  }

}
