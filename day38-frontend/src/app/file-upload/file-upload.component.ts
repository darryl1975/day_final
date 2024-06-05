import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrl: './file-upload.component.css'
})
export class FileUploadComponent {

  myForm = new FormGroup({
    file: new FormControl('', [Validators.required])
  });

  get f() {
    return this.myForm.controls;
  }

  // inject in HttpClient to make API call(s)
  constructor(private http: HttpClient) {}

  submit(): void {

  }

  onFileSelected(event: any) {

  }
}
