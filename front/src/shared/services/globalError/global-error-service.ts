import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class GlobalErrorService {
  errorMessage = signal<string | null>(null);

  showError(message: string) {
    this.errorMessage.set(message);
  }
}
