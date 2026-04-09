import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register-service';
import { GlobalErrorService } from '../../../../shared/services/globalError/global-error-service';
import { FORM_ERROR_MESSAGES } from '../../form.error.messages';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
})
export class LoginForm {
  private serviceRegister = inject(RegisterService);
  private globalErrorService = inject(GlobalErrorService);
  errorMessage: string | null = null;

  loginForm = new FormGroup({
    email: new FormControl('user@user.com', [Validators.required, Validators.email]),
    password: new FormControl('Motdepasse1234#', [Validators.required]),
  });

  getFormError(controlName: string): string | null {
    const control = this.loginForm.get(controlName);

    if (!control || !control.touched || !control.errors) return null;

    const fieldErrors = FORM_ERROR_MESSAGES[controlName] || {};

    for (const errorName in control.errors) {
      console.log(control.errors);
      if (fieldErrors[errorName]) return fieldErrors[errorName];
      if (fieldErrors['condition']) return fieldErrors['condition'];
      if (fieldErrors['exist']) return fieldErrors['exist'];
      if (errorName === 'passwordMismatch') return fieldErrors['passwordMismatch'];
    }

    return null;
  }
}
