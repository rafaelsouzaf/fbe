package com.rafaelsouzaf.fbe.repository;

import com.rafaelsouzaf.fbe.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.company.id=:companyId")
    BigDecimal getAverageSalary(@Param("companyId") Long companyId);

    @Query("SELECT e FROM Employee e WHERE e.company.id=:companyId")
    Optional<List<Employee>> findByCompanyId(@Param("companyId") Long companyId);

}
