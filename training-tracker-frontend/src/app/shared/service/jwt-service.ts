import {Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable()
export class JwtService {

  private static readonly TOKEN_KEY = 'TT-TOKEN';

  jwtHelperService: JwtHelperService;
  decodedToken: string;

  constructor() {
    this.jwtHelperService = new JwtHelperService();
  }

  static getToken() {
    return localStorage.getItem(JwtService.TOKEN_KEY);
  }

  saveToken(token: string) {
    localStorage.setItem(JwtService.TOKEN_KEY, token);
    this.decodedToken = this.jwtHelperService.decodeToken(token);
  }
}
