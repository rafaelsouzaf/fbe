package com.rafaelsouzaf.fbe.controller;

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
    Response get(@PathVariable Long id) {
        return Response.of(companyRepository.findById(id).orElseThrow());
    }

    @PostMapping
    Response save(@RequestBody Company company) {
        return Response.of(companyRepository.save(company));
    }

    @PutMapping
    Response update(@RequestBody Company company) {
        return Response.of(companyRepository.save(company));
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) {
        // TODO check if there are employees vinculated, if not, can delete
        companyRepository.deleteById(id);
    }

    @GetMapping(path = "/average-salary/{id}")
    Response averageSalary(@PathVariable Long id) {
        return Response.of(employeeRepository.getAverageSalary(id).setScale(2, RoundingMode.HALF_EVEN));
    }

}
