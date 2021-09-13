import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';

@Component({
  selector: 'app-member-card',
  templateUrl: './member-card.component.html',
  styleUrls: ['./member-card.component.scss']
})
export class MemberCardComponent implements OnInit {

  constructor(private _location: Location) { }

  backClicked(){
    this._location.back();
  }
  ngOnInit(): void {
  }

}
