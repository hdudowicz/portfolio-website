import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuard implements CanActivate {
  constructor(
    private keycloakService: KeycloakService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return new Promise(async (resolve, _) => {
      if (!this.keycloakService.isLoggedIn()) {
        await this.router.navigate(['/login']);
        return resolve(false);
      }

      const hasRequiredRoles = this.keycloakService.isUserInRole('admin');

      if (hasRequiredRoles) {
        resolve(true);
      } else {
        await this.router.navigate(['/access-denied']);
        resolve(false);
      }
    });
  }
}
