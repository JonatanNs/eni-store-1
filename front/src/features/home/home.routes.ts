import { Routes } from "@angular/router";
import { HomePage } from "./pages/home-page/home.page";
import { RegisterForm } from '../auth/pages/register-form/register-form';
import { LoginForm } from '../auth/pages/login-form/login-form';

export const HOME_ROUTES: Routes = [
  { path: '', component: HomePage, title: 'Accueil' },
  { path: 'auth/inscription', component: RegisterForm, title: 'inscription' },
  { path: 'auth/connexion', component: LoginForm, title: 'connexion' },
];
