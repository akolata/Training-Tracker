export interface SignUpRequest {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
}

export interface SignUpSuccessResponse {
  success: boolean;
  data: string;
}

export interface SignUpFailureResponse {
  success: boolean;
  data: any;
}

export interface SignInRequest {
  usernameOrEmail: string;
  password: string;
}

export interface SignInResponse {
  accessToken: string;
}
