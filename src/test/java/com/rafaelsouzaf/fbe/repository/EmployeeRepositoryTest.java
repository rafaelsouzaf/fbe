package com.rafaelsouzaf.fbe.repository;

import com.rafaelsouzaf.fbe.model.Company;
import com.rafaelsouzaf.fbe.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findAll_thenReturnList() {
        List<Employee> all = employeeRepository.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    public void findById_thenReturnObject() {
        Optional<Employee> obj = employeeRepository.findById((long) 3);
        assertTrue(obj.isPresent());
    }

    @Test
    public void findByIdNotExistent_thenReturnEmpty() {
        Optional<Employee> obj = employeeRepository.findById((long) 0);
        assertTrue(!obj.isPresent());
    }

    @Test
    public void save_thenReturnObject() {
        Company company = new Company();
        company.setId((long)3);

        Employee employee = new Employee();
        employee.setName("Rafael");
        employee.setEmail("rafaelsouzaf@gmail.com");
        employee.setSurname("Souza Fijalkowski");
        employee.setAddress("Street 51, 50823");
        employee.setSalary(BigDecimal.valueOf(100000));
        employee.setCompany(company);

        Employee newEmployee = employeeRepository.save(employee);
        assertTrue(newEmployee.getName().equals(employee.getName()));

        Optional<Employee> optionalEmployee = employeeRepository.findById(newEmployee.getId());
        assertThat(optionalEmployee.isPresent());
    }

    @Test
    public void edit_thenReturnObject() {
        Company company = new Company();
        company.setId((long)3);

        Employee employee = new Employee();
        employee.setId(3l);
        employee.setName("Peter");
        employee.setEmail("rafaelsouzaf@gmail.com");
        employee.setSurname("Souza Fijalkowski");
        employee.setAddress("Street 51, 50823");
        employee.setSalary(BigDecimal.valueOf(100000));
        employee.setCompany(company);

        Employee newEmployee = employeeRepository.save(employee);
        assertTrue(newEmployee.getName().equals(employee.getName()));

        Optional<Employee> optionalEmployee = employeeRepository.findById(newEmployee.getId());
        assertThat(optionalEmployee.isPresent());
        assertThat(optionalEmployee.get().getName().equals(employee.getName()));
    }

    @Test
    public void delete_thenReturnVoid() {
        employeeRepository.deleteById((long)11);
    }

    @Test
    public void deleteNotExistent_thenException() {
        try {
            employeeRepository.deleteById((long)0);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void averageSalary_thenReturnObject() {
        BigDecimal averageSalary = employeeRepository.getAverageSalary((long) 3);
        assertThat(averageSalary).isNotNull();
    }

    @Test
    public void findByCompanyId_thenReturnObject() {
        Optional<List<Employee>> employeeList = employeeRepository.findByCompanyId((long)1);
        assertThat(employeeList).isNotEmpty();
    }

}
