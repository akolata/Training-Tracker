import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GlobalErrorsComponent} from './global-errors/global-errors.component';

@NgModule({
  declarations: [
    GlobalErrorsComponent
  ],
  exports: [
    GlobalErrorsComponent
  ],
  imports: [
    CommonModule,
  ]
})
export class SharedModule {
}
