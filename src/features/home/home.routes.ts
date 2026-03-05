import { Routes } from "@angular/router";
import { AuthPage } from "../auth/pages/auth-page/auth-page";
import { HomePage } from "./pages/home-page/home-page";

export const HOME_ROUTES: Routes = [
    { path: "", component: HomePage, title: "Accueil" },   
    { path: "auth", component: AuthPage, title: "authentification" },

]
