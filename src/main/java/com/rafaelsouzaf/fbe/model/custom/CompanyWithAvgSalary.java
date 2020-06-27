package com.rafaelsouzaf.fbe.model.custom;

import com.rafaelsouzaf.fbe.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyWithAvgSalary extends Company {
    private Double avgSalary;
    public CompanyWithAvgSalary(Company company, Double avgSalary) {
        setId(company.getId());
        setName(company.getName());
        setCreatedAt(company.getCreatedAt());
        this.avgSalary = avgSalary;
    }
}
