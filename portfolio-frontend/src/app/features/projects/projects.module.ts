import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImportsModule } from 'src/app/imports.module';
import { DataViewModule } from 'primeng/dataview';
import { ProjectsComponent } from './projects.component';
import { ProjectCardComponent } from './project-card/project-card.component';
import { KeycloakAngularModule } from 'keycloak-angular';

@NgModule({
  declarations: [ProjectsComponent, ProjectCardComponent],
  imports: [
    CommonModule,
    ImportsModule,
    DataViewModule,
    KeycloakAngularModule
  ],
  exports: [ProjectsComponent]
})
export class ProjectsModule { }
