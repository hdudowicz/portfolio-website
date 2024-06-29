import { APP_INITIALIZER, NgModule } from '@angular/core';
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
import { AboutComponent } from './features/about/about.component';
import { HomeComponent } from './features/home/home.component';
import { FooterComponent } from './features/footer/footer.component';
import { ImportsModule } from './imports.module';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { AnimatedBackgroundComponent } from "./features/shared/components/animated-background/animated-background.component";
import { ScrambleTextEffectComponent } from './features/shared/components/scramble-text-effect/scramble-text-effect.component';
import { ProjectCardComponent } from './features/projects/project-card/project-card.component';
import { ProjectsModule } from './features/projects/projects.module';
import { TerminalTextEffectComponent } from './features/shared/components/terminal-text-effect/terminal-text-effect.component';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { initializeKeycloak } from './core/auth/keycloak-init';
import { KeycloakService } from 'keycloak-angular';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        AboutComponent,
        ContactComponent,
        FooterComponent,
        SidebarComponent,
        ScrambleTextEffectComponent,
        TerminalTextEffectComponent
    ],
    providers: [
        provideHttpClient(withInterceptorsFromDi()),
        {
            provide: APP_INITIALIZER,
            useFactory: initializeKeycloak,
            multi: true,
            deps: [KeycloakService]
        }
    ],
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
        ProjectsModule,
        AnimatedBackgroundComponent
    ]
})
export class AppModule { }