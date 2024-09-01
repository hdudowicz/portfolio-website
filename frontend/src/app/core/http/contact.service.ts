import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";
import { ContactDataDTO } from '../models/contact-data-dto';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private apiUrl = `${environment.apiUrl}/articles`;

  constructor(private http: HttpClient) { }

  submitContact(contactData: ContactDataDTO): Observable<any> {
    return this.http.post(this.apiUrl, contactData, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }); 
  }
}
