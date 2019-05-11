import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Observable, of} from 'rxjs';
import {Action} from '@ngrx/store';
import {Router} from '@angular/router';
import {AuthActionTypes, SignUp, SignUpFailure, SignUpSuccess} from './auth.actions';
import {catchError, exhaustMap, map, switchMap, tap} from 'rxjs/operators';
import {AuthService} from './service/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {SignUpFailureResponse, SignUpSuccessResponse} from './component/model/auth-model';
import {AlertifyService} from "../shared/service/alertify.service";

@Injectable()
export class AuthEffects {

  @Effect()
  public signUp$: Observable<Action> = this.actions$.pipe(
    ofType<SignUp>(AuthActionTypes.SignUp),
    map((action: SignUp) => action.payload),
    switchMap(payload =>
      this.authService
        .signUp(payload.signUpData)
        .pipe(
          map((response: SignUpSuccessResponse) => new SignUpSuccess()),
          catchError((errorResponse: HttpErrorResponse) => {
            console.log('test');
            const serverErrors: SignUpFailureResponse = errorResponse.error;
            const errorMessagesToDisplay: string[] = [];

            let errors = serverErrors.data.errors;
            console.log(errors);
            Object.keys(errors).forEach(error => errorMessagesToDisplay.push(...errors[error]));
            return of(new SignUpFailure({errors: errors}));
          })
        )
    )
  );

  @Effect({dispatch: false})
  public signUpSuccess$: Observable<Action> = this.actions$.pipe(
    ofType<SignUpSuccess>(AuthActionTypes.SignUpSuccess),
    tap(() => this.router.navigateByUrl('/auth/sign-in')),
    tap(() => AlertifyService.notifySuccess('You can sign in now !'))
  );

  constructor(private actions$: Actions, private router: Router, private authService: AuthService) {

  }
}
