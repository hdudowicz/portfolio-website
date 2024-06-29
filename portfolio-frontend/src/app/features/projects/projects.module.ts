import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImportsModule } from 'src/app/imports.module';
import { DataViewModule } from 'primeng/dataview';
import { ProjectsComponent } from './projects.component';
import { ProjectCardComponent } from './project-card/project-card.component';

@NgModule({
  declarations: [ProjectsComponent, ProjectCardComponent],
  imports: [
    CommonModule,
    ImportsModule,
    DataViewModule
  ],
  exports: [ProjectsComponent]
})
export class ProjectsModule { }
