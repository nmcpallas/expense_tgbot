package com.nmcpallas.expense.rest;

import com.nmcpallas.expense.service.ExpenseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("expense")
public class ExpenseRestImpl {

    private ExpenseServiceImpl expenseService;

    @PostMapping("/{category}/expense")
    public void saveExpense(@PathVariable("category") Category category,
                        @RequestParam("description") String description,
                        @RequestParam("amount") Integer amount) {
        expenseService.saveExpense(category, description, amount);
    }

    @PostMapping("/{category}/expectation")
    public void updateExpectation(@PathVariable("category") Category category,
                            @RequestParam("expectation") Integer expectation) {
        expenseService.updateExpectation(category, expectation);
    }

    @GetMapping("/{category}/expense")
    public ExpenseInfoDto getExpenseByCategory(@PathVariable("category") Category category) {
        return expenseService.getByCategory(category);
    }

    @GetMapping("/all")
    public FullExpenseInfoDto getAllExpenses() {
        return expenseService.getAllExpenses();
    }
}
