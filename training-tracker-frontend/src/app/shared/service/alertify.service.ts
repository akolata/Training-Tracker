import * as alertify from 'alertifyjs';

export class AlertifyService {

  private constructor() {
  }

  static notifySuccess(message: string) {
    alertify.success(message);
  }

}
