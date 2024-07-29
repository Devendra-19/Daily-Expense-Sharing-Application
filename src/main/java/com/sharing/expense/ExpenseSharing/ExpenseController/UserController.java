package com.sharing.expense.ExpenseSharing.ExpenseController;

import com.sharing.expense.ExpenseSharing.DummyClasses.LoginAttributes;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.Expense;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.ExpenseParticipants;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.User;
import com.sharing.expense.ExpenseSharing.ExpenseSevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService service ;


    @PostMapping("/registerUser")
    public String userRegistration(@RequestBody Map<String , Object> post){

        return service.userRegistration(post);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody LoginAttributes loginattributes){

        return service.userLogin(loginattributes) ;


    }


}
