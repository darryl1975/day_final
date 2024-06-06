import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent implements OnInit {

  id!: number;
  employee?: Employee;

  employeeForm: FormGroup = new FormGroup({
    id: new FormControl(0),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    profileUrl: new FormControl('')
  });

  constructor(private route: ActivatedRoute, private empSvc: EmployeeService, private router: Router) { }

  get f() {
    return this.employeeForm.controls;
  }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];

    this.empSvc.getEmployeeById(this.id).subscribe(data => {
      // this.employee = data;
      console.log('data >>>' + data.email);


      this.employeeForm.controls["id"].setValue(data.id);
      this.employeeForm.controls["firstName"].setValue(data.firstName);
      this.employeeForm.controls["lastName"].setValue(data.lastName);
      this.employeeForm.controls["email"].setValue(data.email);
      this.employeeForm.controls["profileUrl"].setValue(data.profileURL);

      // this.employeeForm.setValue({id: data.id, firstName: data.firstName, lastName: data.lastName, email: data.email, profileUrl: data.profileUrl });
    });
  }

  submitUpdate(): void {

  }

}
