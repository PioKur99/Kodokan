import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnimationOptions } from 'ngx-lottie';

@Component({
  selector: 'app-registered',
  templateUrl: './registered.component.html',
  styleUrls: ['./registered.component.scss']
})
export class RegisteredComponent implements OnInit {

  constructor(private route:ActivatedRoute) { }

  id:String;

  babkaWokularach: AnimationOptions = {
    path: 'https://assets9.lottiefiles.com/packages/lf20_n5icqxkw.json',
  };

  list = ["Łukasz Ważny", "Ola Kozielska", "Piotr Kurda"];

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
  }

}