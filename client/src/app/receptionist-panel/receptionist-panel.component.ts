import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

//TODO: <Zapewnić, aby cały czas był dostęp do aktualnie zalogowanej recepcjonistki.>

@Component({
  selector: 'app-receptionist-panel',
  templateUrl: './receptionist-panel.component.html',
  styleUrls: ['./receptionist-panel.component.scss']
})
export class ReceptionistPanelComponent implements OnInit {
  loginAuth: boolean = true //tu będzie trzeba przypisać dane z systemu sprawdzania czy jest zalogowany, jeśli ma być zablokowane zmień na false
  constructor(private router: Router) { }

  linkCustomers = [{ outlets: { primary: ['customers'] } }]
  linkEntrance = [{ outlets: { primary: ['entrance'] } }]
  linkMembership = [{ outlets: { primary: ['membership-cards'] } }]
  linkPasses = [{ outlets: { primary: ['passes'] } }]
  linkAddAClient = [{ outlets: { primary: ['add-a-client'] } }]
  linkEntry = [{ outlets: { primary: ['entry-registration'] } }]

  ngOnInit(): void {
    if (this.loginAuth == true) { // jeżeli jest true poprostu odpal stronę 
      return;
    } else { // jeżeli jest false == nie jest zalogowany, wyświetl alert który przekieruje na strone logowania
      if (confirm("Nie jesteś zalogowany!")) {
        this.router.navigate(["/login"]) //tutaj można podmienić ścieżkę do logowania 
      } else {
        this.router.navigate(["/login"]) //tutaj można podmienić ścieżkę do logowania 
      }
    }
  }
}
