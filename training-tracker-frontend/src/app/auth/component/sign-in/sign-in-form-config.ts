import {BaseFormConfig} from '../../../utils/base-form-config';
import {Validators} from '@angular/forms';
import {ControlErrorMessagesBuilder} from '../../../utils/control-config';

export class SignInFormConfig extends BaseFormConfig {

  readonly CONTROL_USERNAME_OR_EMAIL = 'usernameOrEmail';
  readonly CONTROL_PASSWORD = 'password';

  constructor() {
    super();
    this.initControls();
  }

  initControls(): void {
    this.controls.set(this.CONTROL_USERNAME_OR_EMAIL, {
      initialValue: '',
      validators: [Validators.required],
      messages: new ControlErrorMessagesBuilder()
        .required('You must provide username or email')
        .build()
    });
    this.controls.set(this.CONTROL_PASSWORD, {
      initialValue: '',
      validators: [Validators.required],
      messages: new ControlErrorMessagesBuilder()
        .required('Password is required')
        .build()
    });
  }

}
