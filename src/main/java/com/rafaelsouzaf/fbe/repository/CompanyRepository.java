package com.rafaelsouzaf.fbe.repository;

import com.rafaelsouzaf.fbe.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    // TODO next step: Combine several filters; org.springframework.data.jpa.domain.Specification and javax.persistence.criteria.Predicate *may* be useful

}
