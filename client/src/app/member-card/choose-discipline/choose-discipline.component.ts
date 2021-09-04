import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Discipline } from 'src/app/data/discipline';
import { DisciplineService } from 'src/app/services/discipline.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-choose-discipline',
  templateUrl: './choose-discipline.component.html',
  styleUrls: ['./choose-discipline.component.scss']
})
export class ChooseDisciplineComponent implements OnInit {

  public url = 'http://172.18.0.3:8081/discipline';
  public disciplines = [];
  public selected;
  public dialogMessage;
  @ViewChild("dialog2") dialogFailure;
  

  sendDiscipline(){
    this.disciplineService.sendDiscipline(this.selected).subscribe(data => {
      this.router.navigate(["/member-card/prepared-card"])
    },
    error => {
      this.dialogMessage = error.error;
      this.dialogFailure.open();
    })
  }

 constructor(private http: HttpClient, private disciplineService: DisciplineService, private router: Router) {
    this.http.get(this.url).toPromise().then(data => {
      console.log(data);

      for(let key in data)
      if(data.hasOwnProperty(key))
        this.disciplines.push(data[key]);

    });    
   }



  ngOnInit(): void {
  }
 // public disciplines = [
 //   {done: false, description: 'Research' },
 //   { done: false, description: 'Implement' },
 //   { done: false, description: 'Test' }
 // ];
}
