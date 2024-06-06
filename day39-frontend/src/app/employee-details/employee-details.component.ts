import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrl: './employee-details.component.css'
})
export class EmployeeDetailsComponent implements OnInit {

  id!:number;
  employee?: Employee;
  
  constructor(private route: ActivatedRoute, private empSvc: EmployeeService, private router: Router) { }

  ngOnInit(): void {
    // read the URL endpoint route value
    this.id = this.route.snapshot.params['id'];

    this.empSvc.getEmployeeById(this.id).subscribe(data => {
      this.employee = data;
    });
      
  }

  backToList(): void {
    this.router.navigate(['/employees']);
  }

}
