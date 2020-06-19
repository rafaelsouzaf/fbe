package com.rafaelsouzaf.fbe.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "fbe_employee", indexes = { @Index(name = "IDX_employee_company", columnList = "company_id") })
@Data
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

    @NotNull
    private Integer salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt = new Date();

}
