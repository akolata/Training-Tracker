import {Validators} from '@angular/forms';
import {BaseFormConfig} from '../../../utils/base-form-config';
import {ControlErrorMessagesBuilder} from '../../../utils/control-config';

export class SignUpFormConfig extends BaseFormConfig {

  readonly CONTROL_FIRST_NAME = 'firstName';
  readonly CONTROL_LAST_NAME = 'lastName';
  readonly CONTROL_USERNAME = 'username';
  readonly CONTROL_EMAIL = 'email';
  readonly CONTROL_PASSWORD = 'password';

  constructor() {
    super();
    this.initControls();
  }


  initControls(): void {
    this.controls.set(this.CONTROL_FIRST_NAME, {
      initialValue: '',
      validators: [Validators.required, Validators.minLength(2), Validators.maxLength(40)],
      messages: new ControlErrorMessagesBuilder()
        .required('First name is required')
        .minLength('First name should has at least 2 characters')
        .maxLength('First name should has maximum 40 characters')
        .build()
    });
    this.controls.set(this.CONTROL_LAST_NAME, {
      initialValue: '',
      validators: [Validators.required, Validators.minLength(2), Validators.maxLength(40)],
      messages: new ControlErrorMessagesBuilder()
        .required('Last name is required')
        .minLength('Last name should has at least 2 characters')
        .maxLength('Last name should has maximum 40 characters')
        .build()
    });
    this.controls.set(this.CONTROL_USERNAME, {
      initialValue: '',
      validators: [Validators.required, Validators.minLength(4), Validators.maxLength(15)],
      messages: new ControlErrorMessagesBuilder()
        .required('Username is required')
        .minLength('Username should has at least 4 characters')
        .maxLength('Username should has maximum 15 characters')
        .build()
    });
    this.controls.set(this.CONTROL_EMAIL, {
      initialValue: '',
      validators: [Validators.required, Validators.email, Validators.maxLength(40)],
      messages: new ControlErrorMessagesBuilder()
        .required('Email is required')
        .email('Invalid email format')
        .maxLength('Email should has maximum 40 characters')
        .build()
    });
    this.controls.set(this.CONTROL_PASSWORD, {
      initialValue: '',
      validators: [Validators.required, Validators.minLength(6), Validators.maxLength(20)],
      messages: new ControlErrorMessagesBuilder()
        .required('Password is required')
        .minLength('Username should has at least 6 characters')
        .maxLength('Username should has maximum 20 characters')
        .build()
    });
  }

}
