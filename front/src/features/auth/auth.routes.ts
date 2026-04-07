import { Routes } from "@angular/router";
import { HomePage } from "../home/pages/home-page/home.page";
import { AuthPage } from "./pages/auth-page/auth-page";

export const AUTH_ROUTES: Routes = [
    { path: "auth", component: AuthPage, title: "authentification" },
    { path: "", component: HomePage, title: "Accueil" },   

]
