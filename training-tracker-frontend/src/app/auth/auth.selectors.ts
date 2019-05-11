import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AuthState} from './auth.reducer';

export const selectAuthState = createFeatureSelector<AuthState>('auth');

export const isAuthenticated = createSelector(
  selectAuthState,
  (auth: AuthState) => auth.authenticated
);

export const isLoading = createSelector(
  selectAuthState,
  (auth: AuthState) => auth.loading
);

export const getSignUpErrors = createSelector(
  selectAuthState,
  (auth: AuthState) => auth.signUpErrors
);

export const getSignInError = createSelector(
  selectAuthState,
  (auth: AuthState) => auth.signInError
);
