import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './features/shared/components/sidebar/sidebar.component';
import { SidebarModule } from 'primeng/sidebar';
import { ContactComponent } from './features/contact/contact.component';
import { ProjectsComponent } from './features/projects/projects.component';
import { AboutComponent } from './features/about/about.component';
import { HomeComponent } from './features/home/home.component';
import { FooterComponent } from './features/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    ProjectsComponent,
    ContactComponent,
    FooterComponent,
    SidebarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SidebarModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }