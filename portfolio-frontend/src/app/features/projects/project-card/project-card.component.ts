import { Component, Input, OnInit } from '@angular/core';
import { ProjectDTO } from 'src/app/core/models/project-dto';

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.scss']
})
export class ProjectCardComponent implements OnInit {
  @Input() project!: ProjectDTO;
  technologies: string[] = [];

  ngOnInit() {
    this.technologies = this.project.technologies.split(',').map(tech => tech.trim());
  }

  getScreenshotUrl(screenshotId: number): string {
    // Replace this with your actual logic to get the screenshot URL
    return `https://your-api-url/screenshots/${screenshotId}`;
  }
}
