import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { RegisterService } from '../../services/register-service';
import { GlobalErrorService } from '../../../../shared/services/globalError/global-error-service';
import { IUser } from '../../../user/models/IUser';
import { FORM_ERROR_MESSAGES } from '../../form.error.messages';

@Component({
  selector: 'app-register-form',
  imports: [ReactiveFormsModule],
  templateUrl: './register-form.html',
  styleUrl: './register-form.scss',
})
export class RegisterForm {
  private serviceRegister = inject(RegisterService);
  private globalErrorService = inject(GlobalErrorService);
  errorMessage: string | null = null;

  registerForm = new FormGroup(
    {
      firstName: new FormControl('James', [Validators.required, Validators.minLength(2)]),
      lastName: new FormControl('Bond', [Validators.required, Validators.minLength(2)]),
      email: new FormControl('user@user.com', [Validators.required, Validators.email]),
      password: new FormControl('Motdepasse1234#', [
        Validators.required,
        Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\s]).{12,}$/),
      ]),
      confirmPassword: new FormControl('Motdepasse1234#', [Validators.required]),
    },
    { validators: this.matchPasswords('password', 'confirmPassword') },
  );

  matchPasswords(passwordKey: string, confirmPasswordKey: string): ValidatorFn {
    return (group: AbstractControl): ValidationErrors | null => {
      const formGroup = group as FormGroup;
      const password = formGroup.get(passwordKey);
      const confirmPassword = formGroup.get(confirmPasswordKey);

      if (!password || !confirmPassword) return null;
      if (password.value !== confirmPassword.value) {
        confirmPassword.setErrors({ passwordMismatch: true });
      } else {
        confirmPassword.setErrors(null);
      }
      return null;
    };
  }

  onSubmitRegister(): void {
    if (this.registerForm.invalid) return;

    this.serviceRegister.createUser(this.registerForm.value as IUser).subscribe({
      next: (user) => console.log(user),
      error: (err) => {
        const msg = err.error?.message || 'Une erreur est survenue';
        this.globalErrorService.showError(msg);
        console.log(err);
      },
    });
  }

  getFormError(controlName: string): string | null {

    const control = this.registerForm.get(controlName) ;

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
