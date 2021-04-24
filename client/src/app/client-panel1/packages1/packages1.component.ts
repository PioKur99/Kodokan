import { Component, OnInit } from '@angular/core';
import { NorthwindService } from '../../services/northwind.service';

@Component({
  selector: 'app-packages1',
  templateUrl: './packages1.component.html',
  styleUrls: ['./packages1.component.scss']
})
export class Packages1Component implements OnInit {
  public northwindCategories: any[];

  constructor(
    private northwindService: NorthwindService,
  ) {}

  ngOnInit() {
    this.northwindCategories = this.northwindService.getData('Categories');
  }
}
