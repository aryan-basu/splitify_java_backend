package com.aryan.splitify.Repository;

import com.aryan.splitify.Entity.Expense;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository  extends MongoRepository<Expense, ObjectId> {

    List<Expense> findByCreateByOrParticipantsContains(ObjectId createBy, ObjectId participant);

}
