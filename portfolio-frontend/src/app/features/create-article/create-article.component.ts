import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { KeycloakService } from 'keycloak-angular';
import {ImportsModule} from "../../imports.module";
import {ArticleService} from "../../core/http/article.service";
import {catchError} from "rxjs";

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss'],
  imports: [
    ImportsModule
  ],
  standalone: true
})
export class CreateArticleComponent implements OnInit {
  articleForm: FormGroup;
  userId?: string = "";

  constructor(
    private fb: FormBuilder,
    private articleService: ArticleService,
    private keycloakService: KeycloakService
  ) {
    this.articleForm = this.fb.group({
      title: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.getUserId();
  }

  async getUserId() {
    try {
      const userProfile = await this.keycloakService.loadUserProfile();
      this.userId = userProfile.id;
    } catch (error) {
      console.error('Error loading user profile:', error);
    }
  }

  onSubmit() {
    if (this.articleForm.valid) {
      const articleData = {
        ...this.articleForm.value,
        publicationDate: new Date().toISOString(),
        userId: this.userId
      };
      this.articleService.createArticle(articleData).pipe(catchError(err => {
        console.error('Error creating article:', err);
        return err
      })).subscribe(
        (response) => {
          console.log('Article created successfully:', response);
          // Reset form or navigate to article list
          this.articleForm.reset();
        }
      );
    }
  }
}
