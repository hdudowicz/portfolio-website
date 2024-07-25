import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private apiUrl = `${environment.apiUrl}/articles`;

  constructor(private http: HttpClient) { }

  createArticle(articleData: any): Observable<any> {
    return this.http.post(this.apiUrl, articleData, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
