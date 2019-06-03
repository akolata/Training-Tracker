import {async, ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import {SignInComponent} from './sign-in.component';
import {ReactiveFormsModule} from '@angular/forms';
import {By} from '@angular/platform-browser';
import {MaterialModule} from '../../../material.module';
import {Store, StoreModule} from '@ngrx/store';
import * as fromAuth from '../../auth.reducer';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {AppState} from '../../../reducers';
import {SignIn} from '../../auth.actions';

const SIGN_IN_SUBMIT_METHOD = 'onSignInSubmit';

describe('SignInComponent', () => {
  let component: SignInComponent;
  let fixture: ComponentFixture<SignInComponent>;
  let signInBtn;
  let store: Store<AppState>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SignInComponent],
      imports: [
        NoopAnimationsModule,
        MaterialModule,
        ReactiveFormsModule,
        StoreModule.forRoot({}),
        StoreModule.forFeature('auth', fromAuth.authReducer, {initialState: fromAuth.initialAuthState}),
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignInComponent);
    component = fixture.componentInstance;
    signInBtn = fixture.debugElement.query(By.css('#sign-in-btn')).nativeElement;
    store = TestBed.get(Store);

    component.ngOnInit();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('when form is', () => {

    it('invalid then it should have sign-in button disabled', fakeAsync(() => {
      fillSignInForm('', '');
      fixture.detectChanges();

      expect(signInBtn.disabled).toBeTruthy();
      expect(component.signInForm.invalid).toBeTruthy();
    }));

    it('valid then it should have sign-in button enabled', fakeAsync(() => {
      spyOn(component, SIGN_IN_SUBMIT_METHOD);

      fillSignInForm('test@mail.com', 'password');
      fixture.detectChanges();

      expect(component.signInForm.valid).toBeTruthy();
      expect(signInBtn.disabled).not.toBeTruthy();
    }));

    it('then it should call submit method after sign-in button click', fakeAsync(() => {
      spyOn(component, SIGN_IN_SUBMIT_METHOD);

      fillSignInForm('test@mail.com', 'password');
      fixture.detectChanges();
      signInBtn.click();

      expect(component.signInForm.valid).toBeTruthy();
      expect(component.onSignInSubmit).toHaveBeenCalled();
    }));
  });

  describe('when sign in button is clicked', () => {
    it('it should emit SignIn action', () => {
      spyOn(store, 'dispatch');
      const email = 'test@mail.com';
      const password = 'password';
      fillSignInForm(email, password);
      fixture.detectChanges();

      signInBtn.click();

      expect(store.dispatch).toHaveBeenCalledWith(new SignIn({
        signInData: {
          usernameOrEmail: email,
          password
        }
      }));
    });
  });

  function fillSignInForm(usernameOrEmail, password) {
    component.signInForm.setValue({
      usernameOrEmail,
      password
    });
  }
});
