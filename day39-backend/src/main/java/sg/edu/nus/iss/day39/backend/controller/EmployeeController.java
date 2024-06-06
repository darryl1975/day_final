package sg.edu.nus.iss.day39.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.nus.iss.day39.backend.model.Employee;
import sg.edu.nus.iss.day39.backend.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empSvc;

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody Employee employee) {
        Boolean bSaved = empSvc.save(employee);

        return new ResponseEntity<Boolean>(bSaved, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody Employee employee) {
        Integer iUpdated = empSvc.update(employee);

        return new ResponseEntity<Integer>(iUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{employee-id}")
    public ResponseEntity<Integer> delete(@PathVariable("employee-id") Integer id) {
        Integer iDeleted = empSvc.delete(id);

        return new ResponseEntity<Integer>(iDeleted, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = empSvc.findAll();

        // for (Employee employee : employees) {
        //     System.out.println("employee:" + employee.getId() + ">>>" + employee.getProfileURL());
        // }

        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employee-id}")
    public ResponseEntity<Employee> findByEmployeeId(@PathVariable("employee-id") Integer employeeId) {
        System.out.println("@GetMapping({employee-id}) >>> " + employeeId);
        Employee employee = empSvc.findByEmployeeId(employeeId);

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping(path = "/s3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> postEmployeeWithFileToS3(@RequestParam("file") MultipartFile myfile, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email) throws IOException {

        Employee newEmployee = new Employee();
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        newEmployee.setEmail(email);

        System.out.println("file >>> " + myfile.toString());
        System.out.println("firstName >>> " + firstName);
        System.out.println("lastName >>> " + lastName);
        System.out.println("email >>> " + email);

        Boolean result = empSvc.saveWithS3(newEmployee, myfile);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
