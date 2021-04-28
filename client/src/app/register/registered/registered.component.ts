import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registered',
  templateUrl: './registered.component.html',
  styleUrls: ['./registered.component.scss']
})
export class RegisteredComponent implements OnInit {

  memberId;
  registeredUsers = ["Łukasz Ważny", "Ferdynand Kiepski", "Tom Cruise"];

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.memberId = this.route.snapshot.paramMap.get('id');
  }

}
