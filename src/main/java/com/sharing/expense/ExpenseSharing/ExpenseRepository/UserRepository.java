package com.sharing.expense.ExpenseSharing.ExpenseRepository;

import com.sharing.expense.ExpenseSharing.ExpenseEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByName(String username);
}
