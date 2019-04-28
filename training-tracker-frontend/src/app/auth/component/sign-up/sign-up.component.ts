import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signUpForm: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.signUpForm = this.buildSignUpForm();
  }

  onSignUpSubmit() {
    console.log(this.signUpForm.value);
  }

  private buildSignUpForm(): FormGroup {
    return this.fb.group({
      firstName: '',
      lastName: '',
      username: '',
      email: '',
      password: ''
    });
  }

}
