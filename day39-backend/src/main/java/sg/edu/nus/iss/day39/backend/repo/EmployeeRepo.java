package sg.edu.nus.iss.day39.backend.repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day39.backend.model.Employee;

@Repository
public class EmployeeRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String findAllSQL = "select e.id as emp_id, e.first_name, e.last_name, e.email, profile_url from employee e";

    String findByIdSQL = "select e.id, e.first_name, e.last_name, e.email, profile_url from employee e where e.id = ? ";

    String insertSQL = "insert into employee (first_name, last_name, email, profile_url) values (?, ?, ?, ?)";

    String updateSQL = "update employee set first_name = ?, last_name = ?, email = ?, profile_url = ? where id = ? ";

    String deleteSQL = "delete from employee where id = ?";

    public Boolean save(Employee employee) {
        Boolean bSaved = false;

        PreparedStatementCallback<Boolean> psc = new PreparedStatementCallback<Boolean>() {

            @Override
            @Nullable
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getProfileURL());

                Boolean rslt = ps.execute();
                return rslt;
            }

        };

        bSaved = jdbcTemplate.execute(insertSQL, psc);

        return bSaved;
    }

    public int update(Employee employee) {
        int iUpdated = 0;

        PreparedStatementSetter pss = new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getProfileURL());
                ps.setInt(5, employee.getId());
            }
        };

        iUpdated = jdbcTemplate.update(updateSQL, pss);

        return iUpdated;
    }

    public int delete(Integer id) {
        int bDeleted = 0;

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setInt(1, id);
            }
        };

        bDeleted = jdbcTemplate.update(deleteSQL, pss);

        return bDeleted;
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<Employee>();

        employees = jdbcTemplate.query(findAllSQL, new ResultSetExtractor<List<Employee>>() {

            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Employee> emps = new ArrayList<Employee>();

                while (rs.next()) {
                    // e.id as emp_id, e.first_name, e.email, e.profile_url,
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("emp_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    employee.setProfileURL(rs.getString("profile_url"));

                    emps.add(employee);
                }

                return emps;
            }
        });

        return employees;
    }

    public Employee findByEmployeeId(Integer employeeId) {
        System.out.println("EmployeeRepo @findByEmployeeId >>> " + employeeId);
        // Employee employee = new Employee();

        // employee = jdbcTemplate.query(findByIdSQL, new ResultSetExtractor<Employee>() {

        //     @Override
        //     public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
        //         Employee emp = new Employee();

        //         while (rs.next()) {
        //             // e.id as emp_id, e.first_name, e.last_name, e.salary,
        //             Employee employee = new Employee();
        //             employee.setId(rs.getInt("emp_id"));
        //             employee.setFirstName(rs.getString("first_name"));
        //             employee.setLastName(rs.getString("last_name"));
        //             employee.setEmail(rs.getString("email"));
        //             employee.setProfileURL(rs.getString("profile_url"));
        //         }

        //         return emp;
        //     }
        // }, employeeId);

        // return employee;

        return jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Employee.class), employeeId);
    }

}
