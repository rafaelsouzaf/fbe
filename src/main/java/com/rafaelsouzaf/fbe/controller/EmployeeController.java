package com.rafaelsouzaf.fbe.controller;

import com.rafaelsouzaf.fbe.model.Employee;
import com.rafaelsouzaf.fbe.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    List<Employee> get() {
        return employeeRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    Employee get(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @PostMapping
    Employee save(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping
    Employee update(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

}
