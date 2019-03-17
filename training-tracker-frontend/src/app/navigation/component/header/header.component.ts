import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  @Output() sidenavToggle: EventEmitter<void>;

  constructor() {
    this.sidenavToggle = new EventEmitter<void>();
  }

  onToggleSidenav() {
    this.sidenavToggle.emit();
  }
}
