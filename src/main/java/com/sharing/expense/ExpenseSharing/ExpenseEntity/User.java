package com.sharing.expense.ExpenseSharing.ExpenseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;



/*
Structure for Expense Table

ID             |    name           |     email      |    phonenumber      |     password
               |                   |                |                     |

*/


@Entity
@Table(name = "user_table")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID ;
    private String name ;
    private String email ;
    private String phoneNo ;
    private String password ;

    @OneToMany(targetEntity = Expense.class , cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "createdBy" , referencedColumnName = "ID")
    List<Expense> expense ;

    @OneToMany(targetEntity = ExpenseParticipants.class , cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "participant_user_id" ,referencedColumnName = "ID")
    List<ExpenseParticipants> participants ;
}
