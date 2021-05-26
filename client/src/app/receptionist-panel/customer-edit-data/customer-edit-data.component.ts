import { Component, OnInit, ViewChild } from '@angular/core';
import { Client } from 'src/app/data/client';
import { Router } from '@angular/router';
import {Subject, Observable} from 'rxjs';
import { WebcamImage } from 'ngx-webcam';
@Component({
  selector: 'app-customer-edit-data',
  templateUrl: './customer-edit-data.component.html',
  styleUrls: ['./customer-edit-data.component.scss']
})
export class CustomerEditDataComponent implements OnInit {


   editWentGood: boolean = true;
   url: String = "assets/ruda.jpg"
   showWebcam: boolean = false;
   public webImage: WebcamImage = null;
   private trigger: Subject<void> = new Subject<void>();
   @ViewChild("dialog1") dialogSuccess;
   @ViewChild("dialog2") dialogFailure;

   client: Client = {
    name: "Czak",
    surname: "Noris",
    gender: "Men",
    PESEL: "8890991812",
    streetNumb: "Wolf Street 23",
    apartmentNumb: "18",
    city: "Arizona",
    postCode: "00-233",
    voivodeship: "Ślunskie",
    cardNumb: "333224411",
    mail: "czak.noris@gmail.com",
    dyscypline: "Boks",
    phoneNumb: "66677788"

  };

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.navigate(["/receptionist-panel/customer-edit-data", {cardID: this.client.cardNumb}])
  }

  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

  public triggerSnapshot(): void {
    this.trigger.next();
    this.showWebcam = !this.showWebcam;
  }

  manageDialogs() {
    if(this.editWentGood == true) {
      this.dialogSuccess.open();
    }
    else {
      this.dialogFailure.open();
    }
  }

  selectFiles(event) {
    if(event.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0])
      reader.onload = (event:any) => {
        this.url = event.target.result;
      }
    }
  }

  camera () {
    this.showWebcam = !this.showWebcam;
  }

  public handleImage(webcamImage: WebcamImage): void {
    this.webImage = webcamImage;
    this.url = this.webImage.imageAsDataUrl;
  }

}
