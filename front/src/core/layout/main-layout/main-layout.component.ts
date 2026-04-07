import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from '../../../shared/components/navbar/navbar';
import { GlobalAlertComponent } from "../../../shared/components/global-alert-component/global-alert-component";

@Component({
  selector: 'app-main-layout',
  imports: [RouterOutlet, Navbar, GlobalAlertComponent],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.scss',
})
export class MainLayoutComponent {}
