import { Component } from '@angular/core';

@Component({
  selector: 'app-access-denied',
  template: `
    <div class="text-center mt-8">
      <h1 class="text-2xl font-bold text-red-600">Access Denied</h1>
      <p class="mt-4">You do not have permission to access this page.</p>
      <a routerLink="/" class="mt-4 inline-block bg-blue-500 text-white px-4 py-2 rounded">Go to Home</a>
    </div>
  `
})
export class AccessDeniedComponent {}
