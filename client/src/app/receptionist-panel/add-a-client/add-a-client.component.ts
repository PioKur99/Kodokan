import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Location } from '@angular/common';
import { RadioGroupAlignment } from "igniteui-angular";


import {Subject, Observable} from 'rxjs';
import { Client } from '../../data/client';
import { Router } from '@angular/router';
import { WebcamImage } from 'ngx-webcam';
import { ImageCroppedEvent } from 'ngx-image-cropper';

@Component({
  selector: 'app-add-a-client',
  templateUrl: './add-a-client.component.html',
  styleUrls: ['./add-a-client.component.scss']
})
export class AddAClientComponent implements OnInit {

  public alignment = RadioGroupAlignment.vertical;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  isOptional: Boolean;
  makePhoto: Boolean;
  //inputs
  repeatedPassword: String;
  isDisabled: Boolean;
  isChecked: Boolean;
  setTimoutId: any;
  isDifferent: Boolean;
  selected: String;
  displayCamera: Boolean;
  displayCameraCropper: Boolean;
  streetName: String;
  localNumber: String;

  private trigger: Subject<void> = new Subject<void>();
  
  client: Client = {
    addressLine: "",
    city: "",
    email: "",
    firstName: "",
    gender: "",
    identityNumber: "",
    image: null,
    lastName: "",
    password: "",
    phone: "",
    postalCode: "",
    voivodeship: "",
  };
  constructor() { 
    this.isOptional = false;
    this.isDisabled = true;
    this.isChecked = true;   
    this.trigger = new Subject<void>();
    this.setTimoutId = null;
    this.isDifferent = false;
    this.displayCamera = false;
    this.displayCameraCropper = false;
    this.streetName = "";
    this.localNumber = "";
    this.makePhoto = false;
  }

  ngOnInit(): void {
  }

//**************IMAGE CROPPER*************************************************** */
imageChangedEvent: any = '';
croppedImage: any = '';
showCropper = false;

fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.displayCamera = false;
    this.displayCameraCropper = false;
}
imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
    this.client.image = event.base64;
}
imageLoaded() {
  this.showCropper = true;
}

//***************************************************** */
//camera
  //ZRÓB ZDJECIE
  triggerSnapshot(): void {
    this.displayCameraCropper = true;
    this.imageChangedEvent = null;
    this.client.image = null;
    this.makePhoto = false;
    this.trigger.next();
  }


  handleImage(webcamImage: WebcamImage): void {
   this.client.image = webcamImage.imageAsDataUrl;
  }
   
  public get triggerObservable(): Observable<void> {
   return this.trigger.asObservable();
  }

}
