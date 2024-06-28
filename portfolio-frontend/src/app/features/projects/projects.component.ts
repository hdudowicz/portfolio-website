import { Component, OnInit } from '@angular/core';
import { ProjectService } from 'src/app/core/http/project.service';
import { ProjectDTO } from 'src/app/core/models/project-dto';


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
    this.projectService.getProjects().subscribe(
      (data: ProjectDTO[]) => {
        this.projects = data;
      },
      (error: any) => {
        console.error('Error fetching projects:', error);
      }
    );
  }
}
