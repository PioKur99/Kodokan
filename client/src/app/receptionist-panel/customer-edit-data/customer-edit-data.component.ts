import { Component, OnInit, ViewChild } from '@angular/core';
import { Client, Gender } from 'src/app/data/client';
import { ClientService } from 'src/app/services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import {Subject, Observable} from 'rxjs';
import { WebcamImage } from 'ngx-webcam';
@Component({
  selector: 'app-customer-edit-data',
  templateUrl: './customer-edit-data.component.html',
  styleUrls: ['./customer-edit-data.component.scss']
})
export class CustomerEditDataComponent implements OnInit {


   dialogMessage: String;
   url: String = "assets/ruda.jpg"
   showWebcam: boolean = false;
   public webImage: WebcamImage = null;
   private trigger: Subject<void> = new Subject<void>();
   @ViewChild("dialog1") dialogSuccess;
   @ViewChild("dialog2") dialogFailure;

  client: Client = {
   
    addressLine: "Wolf Street 23",
    cardId: "123",
    city: "Arizona",
    email: "czak.noris@gmail.com",
    firstName: "Czak",
    gender: Gender.Male,
    id: 0,
    identityNumber: "333224411",
    image: "",
    lastName: "Noris",
    mainDiscipline: "Boks",
    phone: "66677788",
    postalCode: "00-233",
    voivodeship: "Ślunskie",
    password: "abc"

  };

  constructor(private clientService: ClientService, private router: Router, private urlParam: ActivatedRoute) { }

  ngOnInit(): void {
    //this.getClient(2) // <--- Na potrzeby testów
    this.getClient(parseInt(this.urlParam.snapshot.paramMap.get('id'))) //<--- ID klienta z adresu URL
    //this.router.navigate(["/receptionist-panel/customer-edit-data", {card: this.client.identityNumber}])
  }

  getClient(id: number) : void {
    this.clientService.getClient(id).toPromise().then(data => {this.client = data; this.client.image = "data:image/jpeg;base64," + this.client.image;
      /*this.client.image = this.url /*<--- na potrzeby testów */});
  }

  patchClient() {
    this.client.image = this.client.image.split(",")[1]
    this.clientService.editClient(this.client).subscribe(
      data => {
        this.client = data
        this.dialogSuccess.open();
      },
      error => {
        this.client.image = "data:image/jpeg;base64," + this.client.image;
        this.dialogMessage = error.error;
        this.dialogFailure.open();
      }
    )
  }

  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

  public triggerSnapshot(): void {
    this.trigger.next();
    this.showWebcam = !this.showWebcam;
  }

  selectFiles(event) {
    if(event.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0])
      reader.onload = (event:any) => {
        this.url = event.target.result;
        this.client.image = this.url
      }
    }
  }

  camera () {
    this.showWebcam = !this.showWebcam;
  }

  public handleImage(webcamImage: WebcamImage): void {
    this.webImage = webcamImage;
    this.url = this.webImage.imageAsDataUrl;
    this.client.image = this.url
  }

  closeWindow(id_): void{
      this.dialogSuccess.close();
      this.router.navigate(["/receptionist-panel/customer-data", {id:id_}]);
  }


}
