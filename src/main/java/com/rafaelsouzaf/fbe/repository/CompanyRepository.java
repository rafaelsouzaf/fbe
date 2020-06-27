package com.rafaelsouzaf.fbe.repository;

import com.rafaelsouzaf.fbe.model.Company;
import com.rafaelsouzaf.fbe.model.custom.CompanyWithAvgSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT new com.rafaelsouzaf.fbe.model.custom.CompanyWithAvgSalary(c, AVG(e.salary)) " +
            "FROM Company c " +
            "  JOIN Employee e" +
            "  ON c.id = e.company.id " +
            "GROUP BY c " +
            "ORDER BY c.name")
    Optional<List<CompanyWithAvgSalary>> getAllWithAvgSalary();

}
