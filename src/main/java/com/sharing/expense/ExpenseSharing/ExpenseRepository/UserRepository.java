package com.sharing.expense.ExpenseSharing.ExpenseRepository;

import com.sharing.expense.ExpenseSharing.DummyClasses.UserWithoutPassword;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByName(String username);

    @Query("SELECT new com.sharing.expense.ExpenseSharing.DummyClasses.UserWithoutPassword(u.name, u.email, u.phoneNo) FROM User u")
    List<UserWithoutPassword> findAllUser();
}
