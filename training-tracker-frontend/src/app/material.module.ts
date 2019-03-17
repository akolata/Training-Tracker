import {NgModule} from '@angular/core';
import {MatButtonModule, MatIconModule, MatMenuModule, MatSidenavModule, MatToolbarModule,} from '@angular/material';

@NgModule({
  exports: [
    MatMenuModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class MaterialModule {
}
