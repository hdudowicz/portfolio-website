import {Component, Input} from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-login-button',
  template: `
    <button
      (click)="login()"
      *ngIf="!isLoggedIn"
      class="w-full flex items-center px-4 py-1.5"
      [class.justify-center]="collapsed"
    >
      <i class="pi pi-sign-in mr-4 text-xl" [class.mr-0]="collapsed"></i>
      <span *ngIf="!collapsed">Login</span>
    </button>
    <button
      (click)="logout()"
      *ngIf="isLoggedIn"
      class="w-full flex items-center px-4 py-1.5"
      [class.justify-center]="collapsed"
    >
      <i class="pi pi-sign-out mr-4 text-xl" [class.mr-0]="collapsed"></i>
      <span *ngIf="!collapsed">Logout</span>
    </button>
  `,
})
export class LoginButtonComponent {
  @Input() collapsed = false;
  isLoggedIn = false;

  constructor(private keycloakService: KeycloakService) {
    this.init();
  }

  init() {
    this.isLoggedIn = this.keycloakService.isLoggedIn();
  }

  login() {
    this.keycloakService.login();
  }

  logout() {
    this.keycloakService.logout();
  }
}
