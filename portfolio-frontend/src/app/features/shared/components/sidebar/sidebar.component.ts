import { Component, HostBinding } from '@angular/core';
import { Router } from '@angular/router';

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
export class SidebarComponent {
  
  sidebarItems: SidebarItem[] = [
    { name: 'Home', icon: 'pi pi-home', route: '/home' },
    { name: 'Projects', icon: 'pi pi-briefcase', route: '/projects' },
    { name: 'Contact', icon: 'pi pi-envelope', route: '/contact' },
    { name: 'About', icon: 'pi pi-info-circle', route: '/about' },
  ];

  @HostBinding('class.collapsed') isCollapsed = false;

  constructor(private router: Router) {}

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
