package com.nmcpallas.expense.repository;

import com.nmcpallas.expense.entity.Expectation;
import com.nmcpallas.expense.rest.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultJpaRepository extends JpaRepository<Expectation, Integer> {

    Expectation findByCategory(Category category);
}
