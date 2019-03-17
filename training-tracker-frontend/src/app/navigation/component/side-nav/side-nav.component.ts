import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss']
})
export class SideNavComponent {

  @Output() closeSidenav: EventEmitter<void>;

  constructor() {
    this.closeSidenav = new EventEmitter<void>();
  }

  onClose() {
    this.closeSidenav.emit();
  }
}
