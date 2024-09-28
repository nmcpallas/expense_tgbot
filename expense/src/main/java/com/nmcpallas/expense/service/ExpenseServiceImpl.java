package com.nmcpallas.expense.service;

import com.nmcpallas.expense.entity.Expense;
import com.nmcpallas.expense.entity.Expectation;
import com.nmcpallas.expense.repository.ExpenseJpaRepository;
import com.nmcpallas.expense.repository.ResultJpaRepository;
import com.nmcpallas.expense.rest.Category;
import com.nmcpallas.expense.rest.ExpenseInfoDto;
import com.nmcpallas.expense.rest.FullExpenseInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//сделать сохранение
//сделать так, чтобы можно было доставать по месячно

@Service
@AllArgsConstructor
public class ExpenseServiceImpl {

    private ExpenseJpaRepository expenseRepository;
    private ResultJpaRepository resultRepository;

    public void saveExpense(Category category, String description, Integer amount) {
        Expectation expectation = resultRepository.findByCategory(category);
        expenseRepository.saveAndFlush(Expense.builder().amount(amount).description(description).expectation(expectation).build());
    }

    public ExpenseInfoDto getByCategory(Category category) {
        List<Expense> list = expenseRepository.findAll().stream().filter(e -> e.getExpectation().getCategory().equals(category)).toList();
        return ExpenseInfoDto.builder()
                .category(category)
                .expectation(list.stream().findAny().orElseGet(() -> Expense.builder().build()).getExpectation().getExpectationAmount())
                .result(list.stream().map(Expense::getAmount).reduce(0, Integer::sum))
                .build();
    }

    public FullExpenseInfoDto getAllExpenses() {
        Map<Expectation, List<Expense>> expensesByResult = expenseRepository.findAll()
                .stream().collect(Collectors.groupingBy(Expense::getExpectation));
        List<ExpenseInfoDto> expenses = new ArrayList<>();
        expensesByResult.forEach((k, v) -> expenses.add(mapToExpenseInfo(v)));
        return FullExpenseInfoDto.builder()
                .expenses(expenses)
                .build();
    }

    private ExpenseInfoDto mapToExpenseInfo(List<Expense> expenses) {
        if (expenses.isEmpty())
            return null;
        Expectation expectation = expenses.stream().findAny().get().getExpectation();
        return ExpenseInfoDto.builder()
                .category(expectation.getCategory())
                .expectation(expectation.getExpectationAmount())
                .result(expenses.stream().map(Expense::getAmount).reduce(0, Integer::sum))
                .build();
    }

    public void updateExpectation(Category category, Integer expectation) {
        Expectation result = resultRepository.findByCategory(category);
        result.setExpectationAmount(expectation);
        resultRepository.saveAndFlush(result);
    }
}
