import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ContactService } from 'src/app/core/http/contact.service';
import { catchError } from 'rxjs/operators'
import { of } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { HdMessageService } from '../shared/services/hd-message.service';
import { ContactDataDTO } from 'src/app/core/models/contact-data-dto';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent {
  @Input("contactForm")
  formElement?: HTMLFormElement; 

  contactForm: FormGroup;

  constructor(
    private contactService: ContactService, 
    private fb: FormBuilder,
    private messageService: HdMessageService
    ){
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      message: ['', Validators.required]
    });
  }

  submit(event: ContactDataDTO): void {
    this.contactService.submitContact(event)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this.messageService.submitError(error.message);
          return of();
        })
      ).subscribe(response => {
        console.log('Message submitted successfully:', response);
        this.messageService.submitSuccess(
          'Submission Success', 
          'Message submitted successfully.'
        );
        this.contactForm.reset();
      }
    );
  }
}