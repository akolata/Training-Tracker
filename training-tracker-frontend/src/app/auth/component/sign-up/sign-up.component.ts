import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ValidationUtils} from "../../../utils/validation-utils";
import {SignUpFormConfig} from "./sign-up-form-config";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signUpForm: FormGroup;
  showRequiredValidationMsg = ValidationUtils.showRequiredValidationMsg;
  showValidationMsg = ValidationUtils.showValidationMsg;
  signUpFormConfig: SignUpFormConfig;

  constructor(private fb: FormBuilder) {
    this.signUpFormConfig = new SignUpFormConfig();
  }

  ngOnInit() {
    this.signUpForm = this.buildSignUpForm();
  }

  onSignUpSubmit() {
    console.log(this.signUpForm.value);
  }

  private buildSignUpForm(): FormGroup {
    return this.fb.group(this.signUpFormConfig.getFormConfig());
  }

}
