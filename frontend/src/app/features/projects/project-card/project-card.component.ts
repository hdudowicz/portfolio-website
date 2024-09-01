import {Component, Input, OnChanges, OnInit} from '@angular/core';
import { ProjectDTO } from 'src/app/core/models/project-dto';

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.scss']
})
export class ProjectCardComponent implements OnInit, OnChanges {
  @Input() project!: ProjectDTO;
  placeholderImage: string = '';
  technologies: string[] = [];

  constructor() {}

  ngOnInit(): void {
    if (this.project) {
      this.fetchRandomImage();
    }
  }

  ngOnChanges(): void {
    if (this.project && this.project.technologies) {
      this.technologies = this.project.technologies.split(',').map(tech => tech.trim());
    }
  }

  private fetchRandomImage(): void {
    const width = 300;
    const height = 200;
    this.placeholderImage = `https://picsum.photos/${width}/${height}?random=${this.project.id}`;
  }

  // getImageUrl(): string {
  //   // Return the project's image URL if available, otherwise use the placeholder
  //   return this.project.imageUrl || this.placeholderImage;
  // }
}
