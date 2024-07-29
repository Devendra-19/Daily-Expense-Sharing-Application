package com.sharing.expense.ExpenseSharing.DummyClasses;

public class CalculateUsingDivisionType {


    public double DivisionTypePERCENTAGE(double amount , double percentage){
        return (amount/100)*percentage ;
    }

    public double DivisionTypeEQUALSPLIT(double amount , double noOFPeoples){
        return Math.ceil(amount/noOFPeoples);
    }
}
