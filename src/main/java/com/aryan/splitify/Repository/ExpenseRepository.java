package com.aryan.splitify.Repository;

import com.aryan.splitify.Entity.Expense;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository  extends MongoRepository<Expense, ObjectId> {


}
