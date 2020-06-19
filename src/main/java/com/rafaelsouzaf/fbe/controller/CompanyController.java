package com.rafaelsouzaf.fbe.controller;

import com.rafaelsouzaf.fbe.model.Company;
import com.rafaelsouzaf.fbe.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    List<Company> get() {
        return companyRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    Company get(@PathVariable Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    @PostMapping
    @PutMapping
    Company save(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) {
        // TODO check if there are employees vinculated, if not, can delete
        companyRepository.deleteById(id);
    }

}
