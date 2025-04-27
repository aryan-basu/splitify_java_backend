package com.aryan.splitify.Entity;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Expense {

    private ObjectId id;
    private ObjectId createBy;

    private double amount;

    private String description;

    private List<ObjectId> participants;

    private List<Split> split;



}
