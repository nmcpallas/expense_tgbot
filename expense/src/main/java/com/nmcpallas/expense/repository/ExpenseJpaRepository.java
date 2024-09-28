package com.nmcpallas.expense.repository;

import com.nmcpallas.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseJpaRepository extends JpaRepository<Expense, Integer> {
}
