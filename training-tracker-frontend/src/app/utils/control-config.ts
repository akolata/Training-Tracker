export interface ControlConfig {
  initialValue: any;
  validators;
  messages: ControlErrorMessages;
}

export class ControlErrorMessages {

  static readonly REQUIRED = 'required';
  static readonly MIN_LENGTH = 'minlength';
  static readonly MAX_LENGTH = 'maxlength';
  static readonly EMAIL = 'email';


  messages: Map<string, string> = new Map<string, string>();

  constructor(builder: ControlErrorMessagesBuilder) {
    this.messages = builder.messages;
  }

  get required() {
    return this.messages.get(ControlErrorMessages.REQUIRED);
  }

  getErrorMsg(errorMsg: string) {
    return this.messages.get(errorMsg);
  }

}

export class ControlErrorMessagesBuilder {
  messages: Map<string, string> = new Map<string, string>();

  required(msg): ControlErrorMessagesBuilder {
    this.messages.set(ControlErrorMessages.REQUIRED, msg);
    return this;
  }

  minLength(msg): ControlErrorMessagesBuilder {
    this.messages.set(ControlErrorMessages.MIN_LENGTH, msg);
    return this;
  }

  maxLength(msg): ControlErrorMessagesBuilder {
    this.messages.set(ControlErrorMessages.MAX_LENGTH, msg);
    return this;
  }

  email(msg): ControlErrorMessagesBuilder {
    this.messages.set(ControlErrorMessages.EMAIL, msg);
    return this;
  }

  build(): ControlErrorMessages {
    return new ControlErrorMessages(this);
  }
}
