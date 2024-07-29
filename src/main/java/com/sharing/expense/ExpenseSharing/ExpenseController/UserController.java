package com.sharing.expense.ExpenseSharing.ExpenseController;
import com.sharing.expense.ExpenseSharing.DummyClasses.LoginAttributes;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.User;
import com.sharing.expense.ExpenseSharing.ExpenseSevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service ;


    @GetMapping("/")
    public String checkingAPI(){
        return "Hitting!!" ;
    }
    /*
        Endpoint to register a new user
    */
    @PostMapping("/registerUser")
    public String userRegistration(@RequestBody Map<String , Object> post){

        return service.userRegistration(post);
    }

    // Endpoint for user login
    @PostMapping("/login")
    public String userLogin(@RequestBody LoginAttributes loginattributes){

        return service.userLogin(loginattributes) ;
    }

    // Endpoint to get the details of all the user
    @GetMapping("/alluser")
    List<User> getAllUser(){
        return service.getAlluser();
    }

}
