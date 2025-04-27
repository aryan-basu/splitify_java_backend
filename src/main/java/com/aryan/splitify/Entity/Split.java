package com.aryan.splitify.Entity;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Split {

    private ObjectId id;


    private double amount;
}
