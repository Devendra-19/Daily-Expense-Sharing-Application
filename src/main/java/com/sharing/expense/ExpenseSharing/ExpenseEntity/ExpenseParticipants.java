package com.sharing.expense.ExpenseSharing.ExpenseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
Structure for Expense_Participants Table

participant_id     |    amount         |     split      |    expense_id(expense table)       |     participant_user_id(user table)
                   |                   |                |                                    |

*/

@NoArgsConstructor
@Data
@Entity
@Table(name = "expense_participants")
public class ExpenseParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int participant_id ;
    private double amount ;

    private double split ;

    @ManyToOne
    @JoinColumn(name = "expense_id" ,nullable = false)
    Expense expense ;

    @ManyToOne
    @JoinColumn(name = "participant_user_id" , nullable = false)
    User user ;
}
