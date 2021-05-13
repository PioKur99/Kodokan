import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Location } from '@angular/common';
import { RadioGroupAlignment } from "igniteui-angular";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public alignment = RadioGroupAlignment.vertical;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  isOptional = false;

  constructor(private _formBuilder: FormBuilder, private _location: Location) {}

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required],
      secondCtrl: ['', Validators.required],
      thirdCtrl: ['', Validators.required],
      fourthCtrl: ['', Validators.required],

    });
    this.secondFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required],
      secondCtrl: ['', Validators.required],
      thirdCtrl: ['', Validators.required],
      fourthCtrl: ['', Validators.required],
      fifthCtrl: ['', ],
      sixthCtrl: ['', Validators.required],
      seventhCtrl: ['', Validators.required],
      eighthCtrl: ['', Validators.required],
    });
    this.thirdFormGroup = this._formBuilder.group({

    });
  }

  public navigateBack() {
    this._location.back();
  }

  public canGoBack() {
      return window.history.length > 0;
  }

}
