import {AbstractControl, FormGroup} from '@angular/forms';

export class ValidationUtils {

  static showRequiredValidationMsg(formGroup: FormGroup, formControlName: string): boolean {
    const formControl: AbstractControl = formGroup.get(formControlName);
    return formControl && formControl.hasError('required');
  }

  static showValidationMsg(formGroup: FormGroup, formControlName: string, errorName: string): boolean {
    const formControl: AbstractControl = formGroup.get(formControlName);
    return formControl && !formControl.hasError('required') && formControl.hasError(errorName);
  }
}
