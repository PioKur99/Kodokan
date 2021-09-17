import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IComboSelectionChangeEventArgs } from 'igniteui-angular';
import { filterStates } from 'src/app/data/filter/filter-states';
import { SearchPackage } from 'src/app/data/package/search-package';
import { Passes } from 'src/app/data/passes';
import { PassesService } from 'src/app/services/passes.service';

@Component({
  selector: 'app-passes',
  templateUrl: './passes.component.html',
  styleUrls: ['./passes.component.scss']
})
export class PassesComponent implements OnInit {
  
  public caseSensitive = false;
  public exactMatch = false;
  public filter_states: filterStates[];
  public selectedFilters: number[]
  searchBox: string

  //TODO: zrobić wyszukiwanie karnetów
  passesList: Passes[]
  checkboxes: boolean[]
  deletedPasses: number

  @ViewChild("dialog") dialog;
  @ViewChild("errorGetPasses") errorGetPasses;
  @ViewChild("errorDeletePasses") errorDeletePasses;
  searchPackage: SearchPackage;

  constructor(public passesService: PassesService, private urlParam: ActivatedRoute) {
    this.filter_states=[
      {id: 1,name:"imię i nazwisko"},
      {id: 2,name:"nazwa karnetu"},
      {id: 3,name:"data ważności"},
    ]
   }

  ngOnInit(): void {
    
    this.getPasses(this.urlParam.snapshot.paramMap.get('fullname'))
    
  }
  //pobiera listę karnetów
  getPasses(fName?){
    if(fName){
    this.searchPackage=new SearchPackage()
    this.searchPackage.fullName = fName;
    }
    this.passesService.getPasses(this.searchPackage).subscribe(
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

  singleSelection(event: IComboSelectionChangeEventArgs) {
    if (event.added.length) {
      event.newSelection = event.added;
    } 
  }

  searchCustomers(event){
    console.log("pressenter: ",event.target.value)
    this.searchPackage=new SearchPackage()
    if(this.selectedFilters){
      this.selectedFilters.forEach(
        x=>{
          console.log(x)
          switch(x){
            case 1: {
              this.searchPackage.fullName=event.target.value
              break
            }
            case 2: {
              this.searchPackage.packageName=event.target.value
              break
            }
            case 3: {
              this.searchPackage.endDate=event.target.value
              break
            }
          }
        }
      )
    }else{
      //this.errorFilter.open()
    }
    this.getPasses()
    
  }

}
