package com.aryan.splitify.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DebtDetail {

    private String name;
    private String email;
    private String userId;
    private double amount;
    private String relation;

}
