import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AuthRoutingModule} from './auth-routing.module';
import {SignUpComponent} from './component/sign-up/sign-up.component';
import {SignInComponent} from './component/sign-in/sign-in.component';
import {AuthService} from "./service/auth.service";
import {MaterialModule} from "../material.module";

@NgModule({
  declarations: [
    SignUpComponent,
    SignInComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MaterialModule
  ],
  providers: [
    AuthService
  ]
})
export class AuthModule {
}
