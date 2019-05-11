import {ValidationUtils} from './validation-utils';
import {ControlConfig} from './control-config';
import {FormGroup, ValidationErrors} from '@angular/forms';

export abstract class BaseFormConfig {

  protected controls: Map<string, ControlConfig> = new Map<string, ControlConfig>();
  protected showRequiredValidationMsg = ValidationUtils.showRequiredValidationMsg;
  protected showValidationMsg = ValidationUtils.showValidationMsg;

  protected constructor() {
  }

  abstract initControls(): void;

  showErrorMsg(formGroup: FormGroup, controlName: string) {
    const formControl = formGroup.get(controlName);
    return formControl && formControl.touched && !!formControl.errors;
  }

  getErrorMsg(formGroup: FormGroup, controlName: string) {
    const controlConfig = this.controls.get(controlName);
    if (this.showRequiredValidationMsg(formGroup, controlName)) {
      return controlConfig.messages.required;
    }

    const errors: ValidationErrors = formGroup.get(controlName).errors;
    if (!errors) {
      return;
    }

    /* tslint:disable */
    const errorsKeys = Object.keys(errors);
    for (let i = 0; i < errorsKeys.length; i++) {
      const errorMsg = controlConfig.messages.getErrorMsg(errorsKeys[i]);
      if (errorMsg) {
        return errorMsg;
      }
    }
    /* tslint:enable */

    return `${controlName} error`;
  }

  getFormConfig(): { [p: string]: any } {
    const config = {};

    this.controls.forEach((controlConfig: ControlConfig, controlName: string) => {
      config[controlName] = [controlConfig.initialValue, controlConfig.validators];
    });

    return config;
  }
}
