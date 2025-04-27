package com.aryan.splitify.controller;

import com.aryan.splitify.Entity.Expense;
import com.aryan.splitify.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Document(collection = "Expense")
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @PostMapping("/add-expense")
    public ResponseEntity<?> addExpense(@RequestBody Expense newExpense)
    {
        expenseService.AddExpense(newExpense);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
