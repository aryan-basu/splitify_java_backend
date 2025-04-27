package com.aryan.splitify.Services;

import com.aryan.splitify.Entity.Expense;
import com.aryan.splitify.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {


    @Autowired
    private ExpenseRepository expenseRepository;

    public void AddExpense(Expense expense){

        expenseRepository.save(expense);

    }
}
