package com.aryan.splitify.Entity;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Friends {

    private ObjectId id;
    private String name;
    private String email;
}
