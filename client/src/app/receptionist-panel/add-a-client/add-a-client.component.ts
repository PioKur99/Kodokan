import { Component, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Location } from '@angular/common';
import { RadioGroupAlignment } from "igniteui-angular";
import { Gender } from '../../data/client';

import {Subject, Observable} from 'rxjs';
import { Client } from '../../data/client';
import { Router } from '@angular/router';
import { WebcamImage } from 'ngx-webcam';
import { ImageCroppedEvent } from 'ngx-image-cropper';
import { AddAClientService } from 'src/app/services/add-a-client.service';

@Component({
  selector: 'app-add-a-client',
  templateUrl: './add-a-client.component.html',
  styleUrls: ['./add-a-client.component.scss']
})
export class AddAClientComponent implements OnInit {

  selectedDiscipline: string;

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
  discipline: String;
  discipline_table: String [];
  errorText:String = "";

  private trigger: Subject<void> = new Subject<void>();
  
  client: Client = {
    cardId: "",
    addressLine: "",
    id: null,
    mainDiscipline: "",
    city: "",
    email: "",
    firstName: "",
    gender: Gender.Male,
    identityNumber: "",
    image: "",
    lastName: "",
    password: "",
    phone: "",
    postalCode: "",
    voivodeship: "",
  };

  @ViewChild ("errorDialog") errorDialog;
  @ViewChild ("successDialog") successDialog;
  @ViewChild ("confirmBtn") confirmBtn;

  constructor(private addAClientService: AddAClientService, private router: Router) { 
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
    this.discipline_table = [];
    this.selectedDiscipline="";

  }

  ngOnInit(): void {
    this.getDisciplines();
    console.log(this.selectedDiscipline);
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
    this.checkValidity();
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
  this.checkValidity();
   this.client.image = webcamImage.imageAsDataUrl;
  }
   
  public get triggerObservable(): Observable<void> {
   return this.trigger.asObservable();
  }

  addClient():void{
    this.client.password = this.makePassword(10);
    if(this.localNumber != ""){
      this.client.addressLine = this.streetName + "/" + this.localNumber;
    }else{
      this.client.addressLine = this.streetName;
    }

    this.addAClientService.addClient(this.client).subscribe(
      x=>{

        this.addDiscipline(x);
      },
      error => {
        this.errorDialog.open();
        this.errorText = error.error;
      }
    );
  }

  addDiscipline(id):void{
    this.addAClientService.addDiscipline(id, this.client.mainDiscipline).subscribe(
      x=>{
        this.successDialog.open();
      },
      error => {
        this.errorDialog.open();
        this.errorText = error.error;
      }
    );
  }
  getDisciplines():void{
    this.addAClientService.getDisciplines().subscribe(
      x=>{
        this.discipline_table = x;
      },
      error => {
        this.errorDialog.open();
        this.errorText = error.error;
      }
    );
  }

  closeWindow(type: String): void{
   
    if(type==='error'){
      this.errorDialog.close();
      this.client.image = "data:image/jpeg;base64," + this.client.image;
    }
    else if(type === 'success'){
      this.successDialog.close();
      this.router.navigate(["/receptionist-panel/customers"]);
    }
  }

  makePassword(length): String {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * 
    charactersLength));
      }
      return result;
  }

  checkValidity(): void{
    if(this.streetName !== "" 
        && this.client.city!==  ""
        && this.client.email!==  ""
        && this.client.firstName!==  ""
        && this.client.identityNumber!==  ""
        && this.client.image!==  null
        && this.client.lastName!==  ""
        && this.client.phone!==  ""
        && this.client.postalCode!==  ""
        && this.client.voivodeship!==  ""
        && this.client.mainDiscipline !== ""){

      this.isDisabled = false;
    }
    else  this.isDisabled = true;
  }

  onItemChange(event):void{
    this.client.gender = event.value;
  }

  onDisciplineChange():void{
    console.log(this.client.mainDiscipline);
  }
}




