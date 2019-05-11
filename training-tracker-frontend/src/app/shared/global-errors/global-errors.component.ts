import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-global-errors',
  templateUrl: './global-errors.component.html',
  styleUrls: ['./global-errors.component.scss']
})
export class GlobalErrorsComponent implements OnInit {

  @Input()
  errors: any;

  constructor() {
    console.log(this.errors);
  }

  ngOnInit() {
  }

  getErrors() {
    return Object.keys(this.errors);
  }

}
