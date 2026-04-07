import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { GlobalErrorService } from '../shared/services/globalError/global-error-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
 globalErrorService = inject(GlobalErrorService);
}