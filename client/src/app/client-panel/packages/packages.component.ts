import { Component, OnInit } from '@angular/core';
import { NorthwindService } from '../../services/northwind.service';

@Component({
  selector: 'app-packages',
  templateUrl: './packages.component.html',
  styleUrls: ['./packages.component.scss']
})
export class PackagesComponent implements OnInit {
  public northwindCategories: any[];

  constructor(
    private northwindService: NorthwindService,
  ) {}

  ngOnInit() {
    this.northwindCategories = this.northwindService.getData('Categories');
  }
}
