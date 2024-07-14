import {Component, HostBinding, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, catchError, from, map, Observable, of, Subject, takeUntil} from "rxjs";
import {KeycloakService} from "keycloak-angular";
import {Router} from "@angular/router";

interface SidebarItem {
  name: string;
  icon: string;
  route: string;
}

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent implements OnInit, OnDestroy{
  @HostBinding('class.collapsed') isCollapsed = false;
  private destroy$ = new Subject<void>();
  private sidebarItemsSubject = new BehaviorSubject<SidebarItem[]>([
    { name: 'Home', icon: 'pi pi-home', route: '/home' },
    { name: 'Projects', icon: 'pi pi-briefcase', route: '/projects' },
    { name: 'Contact', icon: 'pi pi-envelope', route: '/contact' },
    { name: 'About', icon: 'pi pi-info-circle', route: '/about' },
  ]);

  sidebarItems$: Observable<SidebarItem[]> = this.sidebarItemsSubject.asObservable();

  constructor(private keycloakService: KeycloakService, private router: Router) {}

  ngOnInit() {
    this.checkAdminRole();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  checkAdminRole() {
    from(this.keycloakService.getUserRoles()).pipe(
      map(roles => roles.includes('admin')),
      catchError(error => {
        console.error('Error checking admin role:', error);
        return of(false);
      }),
      takeUntil(this.destroy$)
    ).subscribe(isAdmin => {
      if (isAdmin) {
        const currentItems = this.sidebarItemsSubject.value;
        this.sidebarItemsSubject.next([
          ...currentItems,
          { name: 'Admin', icon: 'pi pi-cog', route: '/admin' }
        ]);
      }
    });
  }

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
