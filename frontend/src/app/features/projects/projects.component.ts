import { Component, OnInit } from '@angular/core';
import { ProjectService } from 'src/app/core/http/project.service';
import { ProjectDTO } from 'src/app/core/models/project-dto';
import {catchError, of} from "rxjs";


@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit {
  projects: ProjectDTO[] = [];

  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.loadProjects();
  }

  loadProjects() {
    this.projectService.getProjects().pipe(catchError(error => {
      console.error('Error fetching projects:', error)
      //TODO handle error
      return of([])
    })).subscribe(
      (data: ProjectDTO[]) => {
        this.projects = data;
      }
    );
  }
}
