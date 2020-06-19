package com.rafaelsouzaf.fbe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "fbe_employee", indexes = { @Index(name = "IDX_employee_company", columnList = "company_id") })
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 255)
    private String name;

    @NotEmpty
    @Size(max = 255)
    private String surname;

    @Email
    private String email;

    @Size(max = 255)
    private String address;

    @NotEmpty
    private Integer salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty
    @Column(updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    private Date createdAt;

}
