import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {SignInFormConfig} from './sign-in-form-config';
import {Observable} from 'rxjs';
import {select, Store} from '@ngrx/store';
import {AppState} from '../../../reducers';
import {SignIn, SignInLeft} from '../../auth.actions';
import {getSignInError} from '../../auth.selectors';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit, OnDestroy {

  signInForm: FormGroup;
  signInFormConfig: SignInFormConfig;
  signInError: Observable<boolean>;

  constructor(private fb: FormBuilder, private store: Store<AppState>) {
    this.signInFormConfig = new SignInFormConfig();
  }

  ngOnInit() {
    this.signInForm = this.buildSignInForm();
    this.signInError = this.store.pipe(select(getSignInError));
  }

  onSignInSubmit(): void {
    this.store.dispatch(new SignIn({signInData: this.signInForm.value}));
  }

  ngOnDestroy(): void {
    this.store.dispatch(new SignInLeft());
  }

  private buildSignInForm(): FormGroup {
    return this.fb.group(this.signInFormConfig.getFormConfig());
  }
}
