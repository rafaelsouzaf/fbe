package com.rafaelsouzaf.fbe.controller;

import com.rafaelsouzaf.fbe.exception.NotFoundException;
import com.rafaelsouzaf.fbe.model.Employee;
import com.rafaelsouzaf.fbe.model.Response;
import com.rafaelsouzaf.fbe.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    Response get() {
        return Response.of(employeeRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    Response get(@PathVariable Long id) throws NotFoundException {
        return Response.of(employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    @PostMapping
    Response save(@RequestBody Employee employee) {
        return Response.of(employeeRepository.save(employee));
    }

    @PutMapping
    Response update(@RequestBody Employee employee) throws NotFoundException {
        employeeRepository.findById(employee.getId()).orElseThrow(
                () -> new NotFoundException(employee.getId())
        );
        return Response.of(employeeRepository.save(employee));
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) throws NotFoundException {
        employeeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id)
        );
        employeeRepository.deleteById(id);
    }

    @GetMapping(path = "/count")
    Response count() {
        return Response.of(employeeRepository.count());
    }

}
