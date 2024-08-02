import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { KeycloakService } from 'keycloak-angular';
import {ImportsModule} from "../../imports.module";
import {ArticleService} from "../../core/http/article.service";
import {catchError, throwError} from "rxjs";
import {QuillEditorComponent} from "ngx-quill";

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss'],
  imports: [
    ImportsModule,
    QuillEditorComponent
  ],
  standalone: true
})
export class CreateArticleComponent implements OnInit {
  articleForm: FormGroup;
  userId?: string;

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
      // TODO: Improve this
      this.userId = userProfile.id!!;
    } catch (error) {
      console.error('Error loading user profile:', error);
    }
  }
// TODO: Need to create user for this uuid otherwise uuid would would be not found in backend,
//   Need to create registration form, from keycloak maybe?
  onSubmit() {
    if (this.articleForm.valid && this.userId) {
      const articleData = {
        ...this.articleForm.value,
        publicationDate: new Date().toISOString(),
        userId: this.userId
      };
      this.articleService.createArticle(articleData).pipe(
        catchError(err => {
          console.error('Error creating article:', err);
          // TODO: Handle error

          return throwError(() => err);
        })
      ).subscribe({
        next: (response) => {
          console.log('Article created successfully:', response);
          this.articleForm.reset();
        },
        error: (error) => {
          console.error('Error in subscribe:', error);
          // TODO: Handle error
        }
      });
    } else {
      console.error('Form is invalid or userId is not available');
    }
  }
}
