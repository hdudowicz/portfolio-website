import { Component, ViewChild } from '@angular/core';
import { Sidebar, SidebarModule } from 'primeng/sidebar';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],

})
export class SidebarComponent {
  @ViewChild('sidebarRef') sidebarRef!: Sidebar;
  
  sidebarVisible: boolean = true;

    closeCallback(e: Event): void {
        this.sidebarRef.close(e);
    }
}