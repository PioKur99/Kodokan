import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-choose-discipline',
  templateUrl: './choose-discipline.component.html',
  styleUrls: ['./choose-discipline.component.scss']
})
export class ChooseDisciplineComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  public disciplines = [
    {done: true, description: 'Research' },
    { done: true, description: 'Implement' },
    { done: false, description: 'Test' }
  ];
}
