import { Component, inject } from '@angular/core';
import { GlobalErrorService } from '../../services/globalError/global-error-service';

@Component({
  selector: 'app-global-alert-component',
  imports: [],
  templateUrl: './global-alert-component.html',
  styleUrl: './global-alert-component.scss',
})
export class GlobalAlertComponent {
  globalErrorService = inject(GlobalErrorService);

}

