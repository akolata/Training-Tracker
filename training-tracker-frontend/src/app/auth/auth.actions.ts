import {Action} from '@ngrx/store';
import {SignInRequest, SignUpRequest} from './component/model/auth-model';

export enum AuthActionTypes {
  SignUp = '[Auth] Sign Up',
  SignUpSuccess = '[Auth] Sign Up Success',
  SignUpFailure = '[Auth] Sign Up Failure',
  SignUpLeft = '[Auth] Sign Up Left',
  SignIn = '[Auth] Sign In',
  SignInSuccess = '[Auth] Sign In Success',
  SignInFailure = '[Auth] Sign In Failure',
  SignInLeft = '[Auth] Sign In Left',
  Logout = '[Auth] Logout'
}

export class SignUp implements Action {
  readonly type = AuthActionTypes.SignUp;

  constructor(public payload: { signUpData: SignUpRequest }) {
  }
}

export class SignUpSuccess implements Action {
  readonly type = AuthActionTypes.SignUpSuccess;

  constructor() {
  }
}

export class SignUpFailure implements Action {
  readonly type = AuthActionTypes.SignUpFailure;

  constructor(public payload: { errors: any }) {
  }
}

export class SignUpLeft implements Action {
  readonly type = AuthActionTypes.SignUpLeft;
}

export class SignIn implements Action {
  readonly type = AuthActionTypes.SignIn;

  constructor(public payload: SignInRequest) {
  }
}

export class SignInSuccess implements Action {
  readonly type = AuthActionTypes.SignInSuccess;

  constructor(public payload: { jwt: string }) {
  }
}

export class SignInFailure implements Action {
  readonly type = AuthActionTypes.SignInFailure;

  constructor() {
  }
}

export class SignInLeft implements Action {
  readonly type = AuthActionTypes.SignInLeft;
}

export class Logout implements Action {
  readonly type = AuthActionTypes.Logout;

  constructor() {
  }
}

export type AuthActions =
  | SignUp
  | SignUpSuccess
  | SignUpFailure
  | SignUpLeft
  | SignIn
  | SignInSuccess
  | SignInFailure
  | SignInLeft
  | Logout;
