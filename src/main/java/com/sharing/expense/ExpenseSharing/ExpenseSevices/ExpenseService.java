package com.sharing.expense.ExpenseSharing.ExpenseSevices;
import com.sharing.expense.ExpenseSharing.DummyClasses.CalculateUsingDivisionType;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.Expense;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.ExpenseParticipants;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.User;
import com.sharing.expense.ExpenseSharing.ExpenseRepository.ExpenseRepository;
import com.sharing.expense.ExpenseSharing.ExpenseRepository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseJPA ;

    @Autowired
    UserRepository userJPA ;

    @Autowired
    private HttpSession session ;

    @Autowired
    CalculateUsingDivisionType calculateDT ;


    public String addExpense(Expense expense, List<ExpenseParticipants> participants) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new RuntimeException("User is not logged in");
        }
        User user = userJPA.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        expense.setCreatedBy(user);
        int size = participants.size() ;
        double percentage = 0 ;
        for (ExpenseParticipants participant : participants) {
            participant.setExpense(expense);
            if (expense.getDivisionType().equals("PERCENTAGE")) {
                participant.setAmount(calculateDT.DivisionTypePERCENTAGE(expense.getAmount(), participant.getSplit()));
                percentage += participant.getSplit();
            } else if (expense.getDivisionType().equals("EQUAL")) {
                participant.setAmount(calculateDT.DivisionTypeEQUALSPLIT(expense.getAmount(), size));
            }
            User participantUser = userJPA.findByName(participant.getUser().getName());
            if (participantUser != null) {
                participant.setUser(participantUser);
            } else {
                throw new RuntimeException("Participant user not found: " + participant.getUser().getName());
            }
        }
        if(percentage != 100.0){
            throw new RuntimeException("Inappropriate Percentage ");
        }

        expense.setParticipants(participants);
        expenseJPA.save(expense);
        return "Expense added successfully";
    }

    public List<Expense> getExpense() {
        return expenseJPA.findAll() ;
    }



    public List<Expense> getIndividualExpense(String username){
        User user = userJPA.findByName(username) ;
        if(user == null){
            throw new RuntimeException("User Not Found") ;
        }

        return expenseJPA.findByCreatedBy(user) ;


    }
}

