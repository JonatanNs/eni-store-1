import { Routes } from "@angular/router";
import { HomePage } from "../home/pages/home-page/home.page";
import { RegisterForm } from './pages/register-form/register-form';
import { LoginForm } from './pages/login-form/login-form';

export const AUTH_ROUTES: Routes = [
  { path: 'auth/inscription', component: RegisterForm, title: 'inscription' },
  { path: 'auth/connexion', component: LoginForm, title: 'connexion' },
  { path: '', component: HomePage, title: 'Accueil' },
];
