package sg.edu.nus.iss.day39.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.nus.iss.day39.backend.model.Employee;
import sg.edu.nus.iss.day39.backend.repo.EmployeeRepo;
import sg.edu.nus.iss.day39.backend.repo.S3Repo;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepo empRepo;

    @Autowired
    S3Repo s3Repo;

    public Boolean save(Employee employee) {
        return empRepo.save(employee);
    }

    public Boolean saveWithS3(Employee employee, MultipartFile file) {
        
        String s3FileId = s3Repo.saveToS3(file, employee.getFirstName());

        employee.setProfileURL("https://day39.sgp1.digitaloceanspaces.com/"+ s3FileId );
        Boolean isEmployeeSaved = empRepo.save(employee);

        return isEmployeeSaved;
    }

    public int update(Employee employee) {
        return empRepo.update(employee);
    }

    public int delete(Integer id) {
        return empRepo.delete(id);
    }

    public List<Employee> findAll() {
        return empRepo.findAll();
    }

    public Employee findByEmployeeId(Integer employeeId) {
        return empRepo.findByEmployeeId(employeeId);
    }
}
