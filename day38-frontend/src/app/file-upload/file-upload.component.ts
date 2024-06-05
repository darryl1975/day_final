import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrl: './file-upload.component.css'
})
export class FileUploadComponent {

  myForm = new FormGroup({
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });

  get f() {
    return this.myForm.controls;
  }

  // inject in HttpClient to make API call(s)
  constructor(private http: HttpClient) { }

  submit(): void {
    const formData = new FormData();

    const fileSourceValue = this.myForm.get('fileSource')?.value;

    if (fileSourceValue !== null && fileSourceValue !== undefined) {
      formData.append('file', fileSourceValue);
    }

    this.http.post('http://localhost:3000/file-upload', formData)
      .subscribe(response => {
        console.log(response);
        alert('File uploaded successfully!!!');
      })
  }

  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];

      this.myForm.patchValue({
        fileSource: file
      });
    }
  }
}
