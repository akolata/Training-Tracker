import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AuthRoutingModule} from './auth-routing.module';
import {SignUpComponent} from './component/sign-up/sign-up.component';
import {SignInComponent} from './component/sign-in/sign-in.component';
import {AuthService} from "./service/auth.service";
import {MaterialModule} from "../material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthEffects} from "./auth.effects";
import {EffectsModule} from "@ngrx/effects";
import {StoreModule} from "@ngrx/store";
import * as fromAuth from './auth.reducer';
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    SignUpComponent,
    SignInComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    StoreModule.forFeature('auth', fromAuth.authReducer, {initialState: fromAuth.initialAuthState}),
    EffectsModule.forFeature([AuthEffects]),
    SharedModule
  ],
  providers: [
    AuthService
  ]
})
export class AuthModule {
}
