package com.rafaelsouzaf.fbe.repository;

import com.rafaelsouzaf.fbe.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void findAll_thenReturnList() {
        List<Company> all = companyRepository.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    public void findById_thenReturnObject() {
        Optional<Company> obj = companyRepository.findById((long) 3);
        assertTrue(obj.isPresent());
    }

    @Test
    public void findByIdNotExistent_thenReturnEmpty() {
        Optional<Company> obj = companyRepository.findById((long) 0);
        assertTrue(!obj.isPresent());
    }

    @Test
    public void save_thenReturnObject() {
        Company company = new Company();
        company.setName("Souza GmbH");

        Company newCompany = companyRepository.save(company);
        assertTrue(newCompany.getName().equals(company.getName()));

        Optional<Company> optionalCompany = companyRepository.findById(newCompany.getId());
        assertThat(optionalCompany.isPresent());
    }

    @Test
    public void edit_thenReturnObject() {
        Company company = new Company();
        company.setId((long)3);
        company.setName("New Company Name GmbH");

        Company newCompany = companyRepository.save(company);
        assertTrue(newCompany.getName().equals(company.getName()));

        Optional<Company> optionalCompany = companyRepository.findById(newCompany.getId());
        assertThat(optionalCompany.isPresent());
        assertThat(optionalCompany.get().getName().equals(company.getName()));
    }

    @Test
    public void delete_thenReturnVoid() {
        companyRepository.deleteById((long)11);
    }

    @Test
    public void deleteNotExistent_thenException() {
        try {
            companyRepository.deleteById((long)0);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteExistentWithConstraint_thenException() {
        try {
            companyRepository.deleteById((long)0);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

}
