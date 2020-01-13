package com.wd.userapplication.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @GetMapping("status")
    public String status(){
        return "{\"status\":\"web services is up\"}";

    }
    @PostMapping
    public ResponseEntity registerUser(@RequestBody(required = true) User user){
        System.out.println("User firstName...."+ user.getFirstName());

        return new ResponseEntity<User>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

        List<User> userList = new ArrayList<>();

        User user = new User();
        User user1 = new User();
        user.setUserId(123);
        user.setFirstName("Sunidhi");
        user.setLastName("Jain");
        user.setEmailId("sunidhi@gmail.com");
        user.setPhoneNumber("8989227629");

        user1.setUserId(321);
        user1.setFirstName("Sunidhiii");
        user1.setLastName("Jainn");
        user1.setEmailId("sunidhhhi@gmail.com");
        user1.setPhoneNumber("898922767");

       userList.add(user);
       userList.add(user1);
       userList.add(user);
       userList.add(user1);
       userList.add(user);

       return  new ResponseEntity<>(userList,HttpStatus.OK);


    }

    @GetMapping("{userid}")
    public ResponseEntity<User> getUser(@PathVariable(value="userid") int userId){

        User user = new User();

        user.setUserId(123);
        user.setFirstName("Sunidhi");
        user.setLastName("Jain");
        user.setEmailId("sunidhi@gmail.com");
        user.setPhoneNumber("8989227619");

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("{userid}")
    public ResponseEntity deleteUser(@PathVariable(value="userid") int userId){

        System.out.println("delete user id" +userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{userid}")
    public ResponseEntity updateUser(@PathVariable(value="userid") int userId, @RequestBody(required = true) User user){
        System.out.println("Update user api....");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
