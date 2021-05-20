import { Component, OnInit } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  options: AnimationOptions = {
    path: 'assets/dmbgold.json'
  }

  constructor() { }

  ngOnInit(): void {
  }

}
