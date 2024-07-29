package com.sharing.expense.ExpenseSharing.ExpenseEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "expense_table")
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expense_id ;
    private String description ;
    private double amount ;
    private String divisionType ;

    @ManyToOne
    @JoinColumn(name = "ID" , nullable = false)
    User createdBy ;

    @OneToMany(targetEntity = ExpenseParticipants.class , cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "expense_id" , referencedColumnName = "expense_id")
    private List<ExpenseParticipants> participants ;

}
