import * as alertify from 'alertifyjs';//import

export class AlertifyService {

  private constructor() {
  }

  static notifySuccess(message: string) {
    alertify.success(message);
  }

}
