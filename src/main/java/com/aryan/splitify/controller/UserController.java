package com.aryan.splitify.controller;


import com.aryan.splitify.Entity.DebtDetail;
import com.aryan.splitify.Entity.Friends;
import com.aryan.splitify.Entity.User;
import com.aryan.splitify.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String query, @RequestParam(required = false) String lastId) {
        List<User> users;

        if (query != null && !query.trim().isEmpty()) {
            users = userService.searchUsersByNameOrEmail(query);
        } else {
            users = userService.getAll();
        }

        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/friends")
    public ResponseEntity<?> getFriends(@RequestParam(required = false) String query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userService.getuser(userEmail);

        List<Friends> userFriends = user.getFriends(); // Assuming Friends holds ObjectId or minimal info

        if (query != null && !query.trim().isEmpty()) {
            List<User> filteredFriends = userService.searchFriendsByNameOrEmail(userFriends, query);
            return new ResponseEntity<>(filteredFriends, HttpStatus.OK);
        }

        return new ResponseEntity<>(userFriends, HttpStatus.OK);
    }


    @PostMapping("/add-friend/{id}")
    public ResponseEntity<?> addfriend(@PathVariable ObjectId id) {

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userEmail=authentication.getName();






        User newFriend = userService.getUserById(id);
        User currUser = userService.getuser(userEmail);



        if (currUser == null || newFriend == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
// Create Friend object for newFriend
        Friends friendInfo = new Friends();
        friendInfo.setId(newFriend.getId());
        friendInfo.setName(newFriend.getName());
        friendInfo.setEmail(newFriend.getEmail());

// Add to current user's friends list
        currUser.getFriends().add(friendInfo);

// (Optional) - Also add current user as friend to newFriend
        Friends currUserInfo = new Friends();
        currUserInfo.setId(currUser.getId());
        currUserInfo.setName(currUser.getName());
        currUserInfo.setEmail(currUser.getEmail());

        newFriend.getFriends().add(currUserInfo);

// Save both users back
        userService.saveNewUser(currUser);
        userService.saveNewUser(newFriend);

        return new ResponseEntity<>(HttpStatus.OK);
    }




    @GetMapping("/transactions")
        public ResponseEntity<?>getAllTraansaction(){
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userEmail=authentication.getName();

        List<DebtDetail> Transactions=userService.getDebtDetailsForUser(userEmail);

        return new ResponseEntity<>(Transactions,HttpStatus.OK);
        }



}
