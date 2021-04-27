import { Component, OnInit } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';
import { NewClient } from 'src/app/data/client/new-client';
import {HttpClient} from '@angular/common/http';

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

  options: AnimationOptions = {
    path: 'https://assets3.lottiefiles.com/packages/lf20_n5icqxkw.json',
  };

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    console.log(this.http.post<any>("http://172.18.0.3:8081/members", {
      "firstName": "Lukasz",
      "lastName": "Wazny",
      "pesel": "mojpesel"
    }));
  }

}
