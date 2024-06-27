import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent {
  isCollapsed: boolean = false;
  sidebarItems = [
    { name: 'Dashboard', icon: 'pi pi-home' },
    { name: 'Bookmarks', icon: 'pi pi-bookmark' },
    { name: 'Reports', icon: 'pi pi-chart-line' },
    { name: 'Team', icon: 'pi pi-users' },
    { name: 'Messages', icon: 'pi pi-comments' },
    { name: 'Calendar', icon: 'pi pi-calendar' },
    { name: 'Settings', icon: 'pi pi-cog' },
  ];

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }
}
