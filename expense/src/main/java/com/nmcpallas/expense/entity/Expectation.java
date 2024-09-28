package com.nmcpallas.expense.entity;

import com.nmcpallas.expense.rest.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity
@Table(name = "expectation", schema = "money")
public class Expectation extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "expense_category")
    private Category category;
    @Column(name = "expectation_amount")
    private Integer expectationAmount;
}
