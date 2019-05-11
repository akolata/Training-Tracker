import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SignUpComponent} from './sign-up.component';
import {SharedModule} from '../../../shared/shared.module';
import {MaterialModule} from '../../../material.module';
import {ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActionsSubject, ReducerManager, StateObservable, Store, StoreModule} from '@ngrx/store';
import {BehaviorSubject} from 'rxjs';
import {Injectable} from '@angular/core';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import * as fromAuth from '../../auth.reducer';
import {AppState} from '../../../reducers';

@Injectable()
export class MockStore<T> extends Store<T> {
  private stateSubject = new BehaviorSubject<T>({} as T);

  constructor(
    state$: StateObservable,
    actionsObserver: ActionsSubject,
    reducerManager: ReducerManager
  ) {
    super(state$, actionsObserver, reducerManager);
    this.source = this.stateSubject.asObservable();
  }

  setState(nextState: T) {
    this.stateSubject.next(nextState);
  }
}

describe('SignUpComponent', () => {
  let component: SignUpComponent;
  let fixture: ComponentFixture<SignUpComponent>;
  let store: MockStore<AppState>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SignUpComponent],
      imports: [
        NoopAnimationsModule,
        SharedModule,
        MaterialModule,
        ReactiveFormsModule,
        HttpClientTestingModule,
        StoreModule.forRoot({})
      ],
      providers: [
        AuthService,
        {provide: Store, useClass: MockStore}
      ]
    })
      .compileComponents();

    store = TestBed.get(Store);
    store.setState({
      auth: fromAuth.initialAuthState
    });
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
