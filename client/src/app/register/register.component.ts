import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Location } from '@angular/common';
import { RadioGroupAlignment } from "igniteui-angular";
import {WebcamImage} from 'ngx-webcam';
import {Subject, Observable} from 'rxjs';
import { Client } from '../data/client';

  

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
  isOptional: Boolean;
  //inputs
  password: String;
  repeatedPassword: String;
  isDisabled: Boolean;
  isChecked: Boolean;
  setTimoutId: any;
  isDifferent: Boolean;
  selected: String;
    //camera
  //public webcamImage: WebcamImage = null;
  private trigger: Subject<void> = new Subject<void>();

  client: Client = {
    email: "",
    phoneNumber: "",
    sex: "",
    name: "",
    surname: "",
    pesel: "",
    street: "",
    localNumber: null,
    city: "",
    postCode: "",
    province: "",
    image: null
  };

  constructor(private _formBuilder: FormBuilder, private _location: Location) {
    this.isOptional = false;
    this.isDisabled = true;
    this.isChecked = true;   
    this.trigger = new Subject<void>();
    this.setTimoutId = null;
    this.isDifferent = false;

  }

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
  //number input control
  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if ((charCode > 32 && charCode < 48 && charCode !=43 && charCode !=45) || charCode > 57) {
      return false;
    }
    return true;
  }
  //disable or anable next button
  changeBtnState():void{
    if(this.isChecked === true && this.password===this.repeatedPassword) this.isDisabled = false;
    else this.isDisabled = true;
  }
  //password control
  onPressPassword(): void {
    this.changeBtnState();
  }
  displayMessage(): void{
    this.isDifferent = true; 
    console.log("hello")
    console.log(this.isDifferent);
  }
  onSetTimout(): void{
    if(this.setTimoutId){
      clearTimeout(this.setTimoutId);
    }
    if(this.password !== this.repeatedPassword){
      var self = this;
      this.setTimoutId = setTimeout(function(){ 
        self.isDifferent = true; 
      }, 2000);
    }else this.isDifferent = false;
  }

  //checkbox control
  onClickCheckBox(): void{
    this.isChecked = !this.isChecked;
    this.changeBtnState();
  }
  //radio buttons
  onItemChange(event):void{
    console.log(" Value is : ", event.value );
    this.client.sex = event.value;
 }
  //
  onClickNextBtn(): void{
    console.log("jaaaj");

  }

  //camera

  triggerSnapshot(): void {
   this.trigger.next();
  }
  reset(): void{
    this.client.image = null;
  }
  handleImage(webcamImage: WebcamImage): void {
   //console.info('Saved webcam image', webcamImage);
   this.client.image = webcamImage;
  }
   
  public get triggerObservable(): Observable<void> {
   return this.trigger.asObservable();
  }
}
