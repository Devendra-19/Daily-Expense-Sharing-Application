package com.sharing.expense.ExpenseSharing.ExpenseRepository;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.Expense;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


//Here the database operations are handled

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {

    List<Expense> findByCreatedBy(User user) ;
}
