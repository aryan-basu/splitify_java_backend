package com.aryan.splitify.Services;

import com.aryan.splitify.Entity.*;
import com.aryan.splitify.Repository.ExpenseRepository;
import com.aryan.splitify.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


 @Autowired
   private UserRepository userRepository;
 @Autowired
   private ExpenseRepository expenseRepository;


   public void saveNewUser(User user){

       userRepository.save(user);
   }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getuser(String Email){
       return userRepository.findByEmail(Email);
    }
    public User getUserById(ObjectId id) {
        return userRepository.findById(id).orElse(null);
    }
    public List<User> getFriendsDetails(List<String> friendIds) {
        List<ObjectId> objectIds = friendIds.stream()
                .map(ObjectId::new)
                .toList();
        return userRepository.findAllById(objectIds);
    }


    public List<User> searchUsersByNameOrEmail(String query) {
        return userRepository.findByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(query, query);
    }
    public List<User> searchFriendsByNameOrEmail(List<Friends> friends, String query) {
        List<ObjectId> friendIds = friends.stream()
                .map(Friends::getId) // or however you're storing friend references
                .collect(Collectors.toList());

        return userRepository.findByIdInAndNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(friendIds, query, query);
    }


    public List<DebtDetail> getDebtDetailsForUser(String email) {
        User currentUser = userRepository.findByEmail(email);
        ObjectId currentUserId = currentUser.getId();

        List<Expense> expenses = expenseRepository.findByCreateByOrParticipantsContains(currentUserId, currentUserId);
        List<DebtDetail> result = new ArrayList<>();

        for (Expense expense : expenses) {
            ObjectId creatorId = expense.getCreateBy();
            boolean isCreator = creatorId.equals(currentUserId);

            for (Split split : expense.getSplit()) {
                ObjectId otherUserId = split.getId();
                if (otherUserId.equals(currentUserId)) continue; // Skip self

                Optional<User> userOpt = userRepository.findById(otherUserId);
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    double amount = split.getAmount();
                    String relation;

                    // If current user created the expense, others owe current user
                    // If current user is participant, current user owes creator
                    if (isCreator) {
                        relation = "To Receive"; // someone owes you
                    } else {
                        relation = "To Pay"; // you owe someone
                    }

                    result.add(new DebtDetail(
                            user.getName(),
                            user.getEmail(),
                            user.getId().toHexString(),
                            amount,
                            relation
                    ));
                }
            }
        }

        return result;
    }



}
