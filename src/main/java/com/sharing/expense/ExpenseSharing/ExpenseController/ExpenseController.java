package com.sharing.expense.ExpenseSharing.ExpenseController;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.*;
import com.sharing.expense.ExpenseSharing.ExpenseSevices.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService service ;


    // Endpoint to add expense
    @PostMapping("/addExpense")
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
            String username = (String) p.get("username") ;
            if(username == null) return null ;
            participantUser.setName(username);
            participant.setUser(participantUser) ;
            if (divisionType.equals("PERCENTAGE")) {
                participant.setSplit((Double) p.get("split"));
            } else if (divisionType.equals("EXACT")) {
                participant.setAmount(Double.parseDouble(p.get("amount").toString()));
                participant.setSplit(0);
            } else if (divisionType.equals("EQUAL")) {
                participant.setSplit(0);
            }

            return participant;
        }).collect(Collectors.toList());

        String message = service.addExpense(expense, participants);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }



    //Endpoint for retrival of the expenses by all user
    @GetMapping("/allexpense")
    List<Expense> getExpense(){
        return service.getExpense();
    }


    //Endpoint to get expense by username
    @GetMapping("/user/{username}")
    List<Expense> getIndividualExpense(@PathVariable String username){
        return service.getIndividualExpense(username);
    }

}
