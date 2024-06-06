import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:3000/api/employees";

  constructor(private httpClient: HttpClient) { }

  getEmployeeList() : Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseURL}`);
  }

  createEmployee(employee: Employee) : Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, employee);
  }

  createEmployeeWithS3(formData: FormData) : Observable<Object> {
    return this.httpClient.post(`${this.baseURL}` + "/s3", formData);
  }

  getEmployeeById(id: number) : Observable<Employee> {
    return this.httpClient.get<Employee>(`${this.baseURL}/${id}`);
  }

  deleteEmployeeById(id: number) : Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  updateEmployeeById(id: number, employee: Employee) : Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  }
}
