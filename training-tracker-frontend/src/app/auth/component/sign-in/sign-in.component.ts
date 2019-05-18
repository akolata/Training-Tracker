import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {SignInFormConfig} from './sign-in-form-config';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  signInForm: FormGroup;
  signInFormConfig: SignInFormConfig;
  signInErrors$: Observable<string[]>;

  constructor(private fb: FormBuilder) {
    this.signInFormConfig = new SignInFormConfig();
  }

  ngOnInit() {
    this.signInForm = this.buildSignInForm();
  }

  onSignInSubmit(): void {

  }

  private buildSignInForm(): FormGroup {
    return this.fb.group(this.signInFormConfig.getFormConfig());
  }
}
