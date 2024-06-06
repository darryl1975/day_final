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

  employeeForm: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    profileUrl: new FormControl('')
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

  submitSave(): void {
    console.log('create-employee submitSave >>> ' + this.employeeForm.controls['firstName'].value);
    console.log('create-employee submitSave >>> ' +this.employeeForm.controls['lastName'].value);
    console.log('create-employee submitSave >>> ' +this.employeeForm.controls['email'].value);
    console.log('create-employee submitSave >>> ' +this.employeeForm.controls['profileUrl'].value);

    this.employee.firstName = this.employeeForm.controls['firstName'].value;
    this.employee.lastName = this.employeeForm.controls['lastName'].value;
    this.employee.email = this.employeeForm.controls['email'].value;
    this.employee.profileUrl = this.employeeForm.controls['profileUrl'].value;
    console.log('create-employee submitSave >>> ' +this.employee);

    this.empSvc.createEmployee(this.employee).subscribe(data => {
      console.log('create-employee submitSaved >>> ' + data);

      this.router.navigate(['/employees']);
    });
  }

}
