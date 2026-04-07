import { Component, inject } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { FORM_ERROR_MESSAGES } from '../../form.error.messages';
import { IUser } from '../../../user/models/IUser';
import { RegisterService } from '../../services/register-service';
import { GlobalErrorService } from '../../../../shared/services/globalError/global-error-service';

@Component({
  selector: 'app-auth-page',
  imports: [ReactiveFormsModule],
  templateUrl: './auth-page.html',
  styleUrl: './auth-page.scss',
})
export class AuthPage {

  private serviceRegister = inject(RegisterService);
  private globalErrorService = inject(GlobalErrorService);

  registerForm = new FormGroup({
    firstName: new FormControl('James', [Validators.required, Validators.minLength(2)]),
    lastName: new FormControl('Bond', [Validators.required, Validators.minLength(2)]),
    email: new FormControl('user@user.com', [Validators.required, Validators.email]),
    password: new FormControl('Motdepasse1234#', [
      Validators.required,
      Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).{12,}$/)
    ]),
    confirmPassword: new FormControl('Motdepasse1234#', [Validators.required])
  }, { validators: this.matchPasswords('password', 'confirmPassword') }
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

  loginForm = new FormGroup({
    email: new FormControl('user@user.com', [Validators.required, Validators.email]),
    password: new FormControl('Motdepasse1234#', [Validators.required])
  });

  getFormError(controlName: string, formType: 'login' | 'register'): string | null {
    const control = formType === 'login' ? this.loginForm.get(controlName) : this.registerForm.get(controlName);

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

  errorMessage: string | null = null;
  

  onSubmitRegister(): void {
    if (this.registerForm.invalid) return;

    this.serviceRegister.createUser(this.registerForm.value as IUser)
      .subscribe({
        next: (user) => console.log(user),
        error: (err) => {
          const msg = err.error?.message || "Une erreur est survenue";
          this.globalErrorService.showError(msg); 
          console.log(err);
        }
      });
  }
}



