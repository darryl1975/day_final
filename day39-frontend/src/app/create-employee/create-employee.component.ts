import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();

  formBuilder?: FormBuilder;

  file?: File;

  employeeForm: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    profileUrl: new FormControl(''),
    file: new FormControl(''),
    fileSource: new FormControl('')
  });

  ngOnInit(): void {
    // this.employeeForm = this.formBuilder?.group({
    //   firstName: [''],
    //   lastName: [''],
    //   email: [''],
    //   profileUrl: ['']
    // })
  }

  constructor(private empSvc: EmployeeService, private router: Router, formBuilder: FormBuilder) {
    this.formBuilder = formBuilder;
  }

  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      this.file = event.target.files[0];

      const f1= event.target.files[0];

      this.employeeForm.patchValue({
        fileSource: f1
      });
    }
  }

  submitSave(): void {
    console.log('create-employee submitSave >>> ' + this.employeeForm.controls['firstName'].value);
    console.log('create-employee submitSave >>> ' +this.employeeForm.controls['lastName'].value);
    console.log('create-employee submitSave >>> ' +this.employeeForm.controls['email'].value);
    console.log('create-employee submitSave >>> ' +this.employeeForm.controls['profileUrl'].value);
    console.log('create-employee submitSave >>> ' +this.employeeForm.controls['fileSource'].value);

    this.employee.firstName = this.employeeForm.controls['firstName'].value;
    this.employee.lastName = this.employeeForm.controls['lastName'].value;
    this.employee.email = this.employeeForm.controls['email'].value;
    this.employee.profileURL = this.employeeForm.controls['profileUrl'].value;
    console.log('create-employee submitSave >>> ' +this.employee);

    const fileSourceValue = this.employeeForm.get('fileSource')?.value;

    const formData = new FormData();
    if (fileSourceValue !== null && fileSourceValue !== undefined) {
      formData.append('file', fileSourceValue);
    }
    formData.append('firstName', this.employeeForm.controls['firstName'].value);
    formData.append('lastName', this.employeeForm.controls['lastName'].value);
    formData.append('email', this.employeeForm.controls['email'].value);

    this.empSvc.createEmployeeWithS3(formData).subscribe(data => {
      console.log('create-employee submitSaved >>> ' + data);

      this.router.navigate(['/employees']);
    });
  }

}
