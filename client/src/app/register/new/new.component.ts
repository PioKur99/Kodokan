import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { IgxDialogActionsDirective } from 'igniteui-angular/lib/dialog/dialog.directives';
import { Client } from 'src/app/data/client';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.scss']
})
export class NewComponent implements OnInit {

  client:Client = {
    firstName:"",
    lastName:"",
    pesel:""
  };

  @ViewChild("dialog") dialog;

  dialogMessage:String="";
  

  constructor(private registerService : RegisterService, private router:Router) { }

  ngOnInit(): void {

  }

  addClient():void{
    if(this.client.firstName == ""){
      this.dialogMessage = "Imię nie może być puste";
      this.dialog.open();
    }else{
      this.registerService.addClient(this.client).subscribe(
        x => {
          console.log(x);
          this.router.navigate(["/register/registered", {id:x}]);
        },
        error => {
          this.dialogMessage = error.error;
          this.dialog.open();
        }
      );
    }
    
  }


}

