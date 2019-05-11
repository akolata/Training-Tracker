import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {SignUpFormConfig} from './sign-up-form-config';
import {AuthService} from '../../service/auth.service';
import {Observable} from 'rxjs';
import {AppState} from '../../../reducers';
import {select, Store} from '@ngrx/store';
import {SignUp, SignUpLeft} from '../../auth.actions';
import {getSignUpErrors} from '../../auth.selectors';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit, OnDestroy {

  signUpForm: FormGroup;
  signUpFormConfig: SignUpFormConfig;
  signUpErrors$: Observable<string[]>;

  constructor(private fb: FormBuilder, private authService: AuthService, private store: Store<AppState>) {
    this.signUpFormConfig = new SignUpFormConfig();
  }

  ngOnInit() {
    this.signUpForm = this.buildSignUpForm();
    this.signUpErrors$ = this.store.pipe(select(getSignUpErrors));
  }

  onSignUpSubmit() {
    this.store.dispatch(new SignUp({signUpData: this.signUpForm.value}));
  }

  ngOnDestroy() {
    this.store.dispatch(new SignUpLeft());
  }

  private buildSignUpForm(): FormGroup {
    return this.fb.group(this.signUpFormConfig.getFormConfig());
  }

}
