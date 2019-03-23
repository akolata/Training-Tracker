import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatListModule,
  MatMenuModule,
  MatSidenavModule,
  MatToolbarModule,
} from '@angular/material';

@NgModule({
  exports: [
    MatMenuModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class MaterialModule {
}
