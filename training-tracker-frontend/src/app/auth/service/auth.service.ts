import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SignInRequest, SignInResponse, SignUpRequest, SignUpSuccessResponse} from "../component/model/auth-model";
import {environment} from "../../../environments/environment";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  signUp(signUpReq: SignUpRequest): Observable<SignUpSuccessResponse> {
    return this.http.post<SignUpSuccessResponse>(`${environment.api}/api/auth/sign-up`, signUpReq);
  }

  signIn(signInReq: SignInRequest): Observable<SignInResponse> {
    return this.http.post<SignInResponse>(`${environment.api}/api/auth/sign-in`, signInReq);
  }
}
