import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AnimationOptions } from 'ngx-lottie';
import { NewClient } from 'src/app/data/client/new-client';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.scss']
})
export class NewComponent implements OnInit {

  client:NewClient={
    name:"",
    surname:"",
    pesel:"",
    living:"",
    grandma:""
  };

  dialogWindowMessage:String = "";

  @ViewChild('dialog') dialog;

  options: AnimationOptions = {
    path: 'https://assets3.lottiefiles.com/packages/lf20_n5icqxkw.json',
  };

  constructor(private registerService:RegisterService, private router:Router) { }

  ngOnInit(): void {
    
  }

  registerUser(){

    if(this.client.name==""){
      this.dialogWindowMessage = "Musisz podać imię!";
      this.dialog.open();
    } else {
      this.registerService.registerUser(this.client).subscribe(
        message => {
          this.router.navigate(['/register/registered', {id: message}]);
        },
        error => {
          this.dialogWindowMessage = error.error;
          this.dialog.open();
        } 
      )
    }

  }

}
