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
      this.employees = data;
    });
  }



}
