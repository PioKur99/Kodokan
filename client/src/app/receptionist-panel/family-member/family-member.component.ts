import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FamilyMemberService } from '../../services/family-member.service';

@Component({
  selector: 'app-family-member',
  templateUrl: './family-member.component.html',
  styleUrls: ['./family-member.component.scss']
})
export class FamilyMemberComponent implements OnInit {
  
  client_id: String;
  name_surname: String;
  name_to_find: String;
  user_list: Array<any>;
  customer_id_1: String;

  @ViewChild ("dialog") dialog;
  @ViewChild ("successDialog") successDialog;

  findNameById():void{
    this.familyMemberService.findCustomerById(this.client_id).subscribe(
      resp => {
        this.name_surname = resp.firstName+resp.lastName;
      },
      err => {
      }
    );
  }
  findUsers():void{
    this.familyMemberService.findUsers(this.name_to_find).subscribe(
      resp => {
        this.user_list = resp.slice(0, 10);
      },
      err => {
      }
    );
  }
  onAdd(customer_id: String){
    this.dialog.open();
    this.customer_id_1 = customer_id;
  }
  onAddRelation(relation: String):void{
    this.familyMemberService.addRelation(this.customer_id_1, this.client_id, relation).subscribe(
      resp => {
        this.successDialog.open();
      },
      err => {
      }
    );
  }
  constructor(private route:ActivatedRoute, private familyMemberService: FamilyMemberService, private router: Router) { }

  ngOnInit(): void {
    this.client_id = this.route.snapshot.paramMap.get("id");
    this.findNameById();
  }

}
