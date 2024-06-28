import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './features/shared/components/sidebar/sidebar.component';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { AvatarModule } from 'primeng/avatar';
import { StyleClassModule } from 'primeng/styleclass';
import { ContactComponent } from './features/contact/contact.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProjectsComponent } from './features/projects/projects.component';
import { AboutComponent } from './features/about/about.component';
import { HomeComponent } from './features/home/home.component';
import { FooterComponent } from './features/footer/footer.component';
import { ImportsModule } from './imports.module';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { AnimatedBackgroundComponent } from "./features/shared/components/animated-background/animated-background.component";

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
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        SidebarModule,
        ButtonModule,
        RippleModule,
        AvatarModule,
        StyleClassModule,
        BrowserAnimationsModule,
        DragDropModule,
        ImportsModule,
        AnimatedBackgroundComponent
    ]
})
export class AppModule { }