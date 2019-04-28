import {Validators} from "@angular/forms";
import {BaseFormConfig} from "../../../utils/base-form-config";

export class SignUpFormConfig extends BaseFormConfig {

  readonly CONTROL_FIRST_NAME = 'firstName';
  readonly CONTROL_LAST_NAME = 'lastName';
  readonly CONTROL_USERNAME = 'username';
  readonly CONTROL_EMAIL = 'email';
  readonly CONTROL_PASSWORD = 'password';

  getFormConfig(): { [p: string]: any } {
    const config = {};

    config[this.CONTROL_FIRST_NAME] = [
      '',
      [Validators.required, Validators.minLength(2), Validators.maxLength(40)]
    ];
    config[this.CONTROL_LAST_NAME] = [
      '',
      [Validators.required, Validators.minLength(2), Validators.maxLength(40)]]
    ;
    config[this.CONTROL_USERNAME] = [
      '',
      [Validators.required, Validators.minLength(2), Validators.maxLength(40)]
    ];
    config[this.CONTROL_EMAIL] = [
      '',
      [Validators.required, Validators.email]
    ];
    config[this.CONTROL_PASSWORD] = ['', Validators.required];

    return config;
  }

}
