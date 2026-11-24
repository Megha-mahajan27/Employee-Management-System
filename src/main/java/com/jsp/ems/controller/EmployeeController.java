package com.jsp.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ems.entity.Employee;
import com.jsp.ems.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService es;

    public EmployeeController(EmployeeService es) {
        this.es = es;
    }

    @PostMapping("/hello")
    public String create(@Valid @RequestBody Employee employee) {
        return es.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public Object fetchById(@PathVariable String email) {
        return es.fetchById(email);
    }

    @GetMapping("/fetchAll")
    public List<Employee> fetchAll() {
        return es.fetchAll();
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable String email) {
        return es.deleteById(email);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String email,@RequestBody Employee employee) {
    	employee.setEmail(email);
    	return es.update(employee);
    }
}