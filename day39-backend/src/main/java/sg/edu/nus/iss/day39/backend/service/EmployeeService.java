package sg.edu.nus.iss.day39.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day39.backend.model.Employee;
import sg.edu.nus.iss.day39.backend.repo.EmployeeRepo;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepo empRepo;

    public Boolean save(Employee employee) {
        return empRepo.save(employee);
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
