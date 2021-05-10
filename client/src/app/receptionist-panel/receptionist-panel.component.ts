import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-receptionist-panel',
  templateUrl: './receptionist-panel.component.html',
  styleUrls: ['./receptionist-panel.component.scss']
})
export class ReceptionistPanelComponent implements OnInit {

  constructor() { }

  linkCustomers = [{ outlets: { primary: ['customers'] } }]
  linkEntrance = [{ outlets: { primary: ['entrance'] } }]
  linkMembership = [{ outlets: { primary: ['membership-cards'] } }]
  linkPasses = [{ outlets: { primary: ['passes'] } }]
  linkAddAClient = [{ outlets: { primary: ['add-a-client'] } }]
  linkEntry = [{ outlets: { primary: ['entry-registration'] } }]
  ngOnInit(): void {
  }

}
