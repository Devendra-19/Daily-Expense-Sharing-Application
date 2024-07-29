package com.sharing.expense.ExpenseSharing.DummyClasses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutPassword {
    private String name ;
    private String email ;
    private String password ;
}
