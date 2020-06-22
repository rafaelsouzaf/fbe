package com.rafaelsouzaf.fbe.controller;

import com.rafaelsouzaf.fbe.exception.NotFoundException;
import com.rafaelsouzaf.fbe.model.Company;
import com.rafaelsouzaf.fbe.model.Response;
import com.rafaelsouzaf.fbe.repository.CompanyRepository;
import com.rafaelsouzaf.fbe.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;

@RestController
@RequestMapping(path = "/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    Response get() {
        return Response.of(companyRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    Response get(@PathVariable Long id) throws NotFoundException {
        return Response.of(companyRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id)
        ));
    }

    @PostMapping
    Response save(@RequestBody Company company) {
        return Response.of(companyRepository.save(company));
    }

    @PutMapping
    Response update(@RequestBody Company company) throws NotFoundException {
        companyRepository.findById(company.getId()).orElseThrow(
                () -> new NotFoundException(company.getId())
        );
        return Response.of(companyRepository.save(company));
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) throws NotFoundException {
        companyRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id)
        );
        companyRepository.deleteById(id);
    }

    @GetMapping(path = "/average-salary/{id}")
    Response averageSalary(@PathVariable Long id) throws NotFoundException {
        companyRepository.findById(id).orElseThrow(
                () -> new NotFoundException(id)
        );
        return Response.of(employeeRepository.getAverageSalary(id).setScale(2, RoundingMode.HALF_EVEN));
    }

}
