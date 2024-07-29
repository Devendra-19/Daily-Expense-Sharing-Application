package com.sharing.expense.ExpenseSharing.ExpenseSevices;
import com.sharing.expense.ExpenseSharing.DummyClasses.CalculateUsingDivisionType;
import com.sharing.expense.ExpenseSharing.ExpenseEntity.*;
import com.sharing.expense.ExpenseSharing.ExpenseRepository.* ;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.util.List;

//All the logic related to expense is handled by this class


@Service
public class ExpenseService {

    private final ExpenseRepository expenseJPA;
    private final UserRepository userJPA;
    private final HttpSession session;
    private final CalculateUsingDivisionType calculateDT;

    public ExpenseService(ExpenseRepository expenseJPA, UserRepository userJPA, HttpSession session, CalculateUsingDivisionType calculateDT) {
        this.expenseJPA = expenseJPA;
        this.userJPA = userJPA;
        this.session = session;
        this.calculateDT = calculateDT;
    }

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
        if(user == null) {
            throw new RuntimeException("User Not Found");
        }
        return expenseJPA.findByCreatedBy(user) ;
    }
}

