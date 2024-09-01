import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";
import {ArticleRequestDTO} from "../models/article-request-dto";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private apiUrl = `${environment.apiUrl}/articles`;

  constructor(private http: HttpClient) { }

  createArticle(articleData: ArticleRequestDTO): Observable<any> {
    return this.http.post(this.apiUrl, articleData, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
