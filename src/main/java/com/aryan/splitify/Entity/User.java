package com.aryan.splitify.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId id;  // id field should be lowercase by convention

    private String name;  // name field should be lowercase

    @Indexed(unique = true)  // 🔥 Unique constraint on email
    private String email; // email field should be lowercase
    private String password;

    private List<String> friends = new ArrayList<>();  // Default empty list
}
