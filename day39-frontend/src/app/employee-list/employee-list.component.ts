import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { Employee } from '../employee';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit {

  employees?: Employee[];

  constructor(private empSvc: EmployeeService, private router: Router) { }

  ngOnInit(): void {
    this.empSvc.getEmployeeList().subscribe(data => {
      // console.log("employee  list>>> " + data)

      for (let i = 0; i < data.length; i++) {
        console.log("employee >>> " + data[i].id + " >>> " + data[i].profileURL)
      }
      this.employees = data;
    });
  }

  deleteEmployee(id: number) {
    console.log("employee  list delete record id >>> " + id);

    // call API using empSvc to perform the delete of the record
  }

  showEmployeeDetails(id: number) {
    console.log("employee  list show details record id >>> " + id);
    this.router.navigate([`/employee-details/${id}`]);
  }

  updateEmployee(id: number) {
    this.router.navigate([`/update-employee/${id}`]);
  }
}
