import {AuthActions, AuthActionTypes} from './auth.actions';

export interface AuthState {
  authenticated: boolean;
  signUpErrors?: string[];
  signInError?: boolean;
  loading: boolean;
}

export const initialAuthState: AuthState = {
  authenticated: false,
  loading: false
};

export function authReducer(state: AuthState = initialAuthState, action: AuthActions): AuthState {

  switch (action.type) {

    case AuthActionTypes.SignUp:
      return Object.assign({}, state, {
        authenticated: false,
        signInError: false,
        loading: true
      });

    case AuthActionTypes.SignUpSuccess:
      return Object.assign({}, state, {
        loading: false,
        signUpErrors: undefined
      });

    case AuthActionTypes.SignUpFailure:
      return Object.assign({}, state, {
        signUpErrors: action.payload.errors,
        loading: false
      });

    case AuthActionTypes.SignUpLeft:
      return Object.assign({}, state, {
        signUpErrors: undefined
      });

    case AuthActionTypes.SignIn:
      return Object.assign({}, state, {
        authenticated: false,
        signUpErrors: undefined,
        loading: true
      });

    case AuthActionTypes.SignInSuccess:
      return Object.assign({}, state, {
        authenticated: true,
        loading: false,
        signInError: false
      });

    case AuthActionTypes.SignInFailure:
      return Object.assign({}, state, {
        signInError: true,
        loading: false
      });

    case AuthActionTypes.SignInLeft:
      return Object.assign({}, state, {
        signInError: false
      });

    case AuthActionTypes.Logout:
      return Object.assign({}, state, {
        authenticated: false
      });

    default:
      return state;
  }

}
