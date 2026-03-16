import { Routes } from '@angular/router';
import { MainLayoutComponent } from '../core/layout/main-layout/main-layout.component';
import { HOME_ROUTES } from '../features/home/home.routes';
import { AUTH_ROUTES } from '../features/auth/auth.routes';

export const routes: Routes = [
  { path: "", component: MainLayoutComponent, children : HOME_ROUTES},
  { path: "auth", component: MainLayoutComponent, children : AUTH_ROUTES},
  
  //{ path: "users",loadChildren: () =>import('').then(m => m.UsersModule) },
  
  // redirection si URL vide
  { path:'', redirectTo:'home', pathMatch:'full' },
];