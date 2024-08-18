import { Injectable } from '@angular/core';
import { Message, MessageService } from 'primeng/api';

/**
 * Convenience message service class with functions to easier handle submission of 
 * PrimeNG messages.
 */
@Injectable({
  providedIn: 'root'
})
export class HdMessageService extends MessageService{

  constructor() { 
    super();
  }

  submitError(details: string): void {
    this.clear();
    this.add({
      severity: 'error',
      summary: 'Error',
      detail: details
    });
  }

  submitSuccess(title: string, details: string): void {
    this.clear();
    this.add({
      severity: 'success',
      summary: title,
      detail: details
    });
  }
}
