package com.nmcpallas.expense.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity
@Table(name = "expense", schema = "money")
public class Expense extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "expectation_id")
    private Expectation expectation;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private String description;
}
