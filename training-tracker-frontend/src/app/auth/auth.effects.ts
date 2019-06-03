import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Observable, of} from 'rxjs';
import {Action} from '@ngrx/store';
import {Router} from '@angular/router';
import {
  AuthActionTypes,
  SignIn,
  SignInFailure,
  SignInSuccess,
  SignUp,
  SignUpFailure,
  SignUpSuccess
} from './auth.actions';
import {catchError, map, switchMap, tap} from 'rxjs/operators';
import {AuthService} from './service/auth.service';
import {HttpErrorResponse} from '@angular/common/http';
import {SignInResponse, SignUpFailureResponse, SignUpSuccessResponse} from './component/model/auth-model';
import {AlertifyService} from '../shared/service/alertify.service';
import {JwtService} from '../shared/service/jwt-service';

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

            const errors = serverErrors.data.errors;
            console.log(errors);
            Object.keys(errors).forEach(error => errorMessagesToDisplay.push(...errors[error]));
            return of(new SignUpFailure({errors}));
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

  @Effect()
  public signIn$: Observable<Action> = this.actions$.pipe(
    ofType<SignIn>(AuthActionTypes.SignIn),
    map((action: SignIn) => action.payload),
    switchMap(payload =>
      this.authService.signIn(payload.signInData)
        .pipe(
          map((response: SignInResponse) => new SignInSuccess({jwt: response.accessToken})),
          catchError(() => of(new SignInFailure()))
        ))
  );

  @Effect({dispatch: false})
  public signInSuccess$: Observable<Action> = this.actions$.pipe(
    ofType<SignInSuccess>(AuthActionTypes.SignInSuccess),
    tap((action: SignInSuccess) => this.jwtService.saveToken(action.payload.jwt)),
    tap(() => this.router.navigateByUrl('/home'))
  );

  constructor(
    private actions$: Actions,
    private router: Router,
    private authService: AuthService,
    private jwtService: JwtService) {
  }
}
