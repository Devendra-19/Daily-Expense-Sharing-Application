package com.sharing.expense.ExpenseSharing.ExpenseRepository;

import com.sharing.expense.ExpenseSharing.ExpenseEntity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
}
