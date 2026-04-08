import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginForm } from '../../components/login-form/login-form';
import { RegisterForm } from '../../components/register-form/register-form';

@Component({
  selector: 'app-auth-page',
  imports: [ReactiveFormsModule, LoginForm, RegisterForm],
  templateUrl: './auth-page.html',
  styleUrl: './auth-page.scss',
})
export class AuthPage {}



