package com.jsp.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ems.Repository.EmployeeRepository;
import com.jsp.ems.entity.Employee;

@Service
public class EmployeeService {

    EmployeeRepository er;

    public EmployeeService(EmployeeRepository er) {
        this.er = er;
    }

    public String createEmployee(Employee employee) {
        er.save(employee);
        return "employee added successfully";
    }

    public Object fetchById(String email) {

        Optional<Employee> emp = er.findById(email);

        if (emp.isPresent()) {
            return emp.get();
        } else {
            return "Employee not found";
        }
    }

    public List<Employee> fetchAll() {
        return er.findAll();
    }

    public String deleteById(String email) {

        if (er.existsById(email)) {
            er.deleteById(email);
            return "data deleted";
        }

        return "data not found";
    }

    public String update(Employee employee) {
        er.save(employee);
        return "Employee updated Successful";
    }
}