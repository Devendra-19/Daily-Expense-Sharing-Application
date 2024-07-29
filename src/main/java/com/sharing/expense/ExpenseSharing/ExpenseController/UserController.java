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

    @PostMapping("/expense")
    public Map<String, String> addExpense(@RequestBody Map<String, Object> request) {
        String description = (String) request.get("description");
        double amount = Double.parseDouble(request.get("amount").toString());
        String divisionType = (String) request.get("DivisionType");
        List<Map<String, Object>> participantsData = (List<Map<String, Object>>) request.get("participants");

        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setDivisionType(divisionType);

        List<ExpenseParticipants> participants = participantsData.stream().map(p -> {
            ExpenseParticipants participant = new ExpenseParticipants() ;
            User participantUser = new User();
            participantUser.setName((String) p.get("username"));
            participant.setUser(participantUser);
            if(divisionType == "PERCENTAGE"){
                participant.setSplit((Double)p.get("split"));
            }
            else if(divisionType == "EXACT"){
                participant.setAmount(Double.parseDouble(p.get("amount").toString()));
                participant.setSplit(0);
            }
            else if(divisionType == "EQUAL"){
                participant.setSplit(0);
            }
            return participant;
        }).collect(Collectors.toList());

        String message = service.addExpense(expense, participants);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }


    @GetMapping("/allexpense")
    List<Expense> getExpense(){
        return service.getExpense();
    }
}
