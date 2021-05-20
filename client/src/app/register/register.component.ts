import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Location } from '@angular/common';
import { RadioGroupAlignment } from "igniteui-angular";
import {WebcamImage} from 'ngx-webcam';
import {Subject, Observable} from 'rxjs';
import { Client } from '../data/client';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';
import { ImageCroppedEvent } from 'ngx-image-cropper';
  

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
  displayCamera: Boolean;
  displayCameraCropper: Boolean;
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
    image: null,
    image64: ""
  };

  constructor(private _formBuilder: FormBuilder, private _location: Location, private registerService: RegisterService, private router: Router) {
    this.isOptional = false;
    this.isDisabled = true;
    this.isChecked = true;   
    this.trigger = new Subject<void>();
    this.setTimoutId = null;
    this.isDifferent = false;
    this.displayCamera = false;
    this.displayCameraCropper = false;

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
//**************IMAGE CROPPER*************************************************** */
    imageChangedEvent: any = '';
    croppedImage: any = '';
    showCropper = false;


    
    fileChangeEvent(event: any): void {
        console.log("EVENT");
        console.log(event);
        this.imageChangedEvent = event;
        this.displayCamera = false;
        this.displayCameraCropper = false;
    }
    imageCropped(event: ImageCroppedEvent) {
        console.log("CROPPED EVENT");
        console.log(event);

        this.croppedImage = event.base64;
        this.client.image64 = event.base64;
    }
    imageLoaded() {
      this.showCropper = true;
      console.log('Image loaded');
        // show cropper
    }
    cropperReady() {
        // cropper ready
    }
    loadImageFailed() {
        // show message
    }
//***************************************************** */
  addClient():void{
    this.registerService.addClient(this.client).subscribe(
      x=>{
        this.router.navigate(["/register", {id:x}]);
      },
      error => {
        this.router.navigate(["/"]);
        console.log(error);
      }
    );
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
  //ZRÓB ZDJECIE
  triggerSnapshot(): void {
    this.imageChangedEvent = null;
    if(this.displayCamera===false){
      this.client.image = null;
      this.client.image64 = null;
      this.displayCameraCropper = true;
    }
    this.displayCamera = !this.displayCamera;
    this.trigger.next();
  }

  reset(): void{
    this.imageChangedEvent = null;
    this.displayCamera = false;
    this.displayCameraCropper = false;
    this.client.image = null;
    this.client.image64 = null;
  }

  handleImage(webcamImage: WebcamImage): void {
   //console.info('Saved webcam image', webcamImage);
   this.displayCameraCropper = true;
   this.client.image = webcamImage;
   this.client.image64 = webcamImage.imageAsDataUrl;
   console.log(this.client.image);
  // this.image = webcamImage.imageAsDataUrl;
  }
   
  public get triggerObservable(): Observable<void> {
   return this.trigger.asObservable();
  }
}
