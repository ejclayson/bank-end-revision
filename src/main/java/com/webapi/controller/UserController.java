package com.webapi.controller;

import com.webapi.entity.User;
import com.webapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // This means that this class is a Controller
@RequestMapping(path="/bank") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser(@RequestParam String name
            , @RequestParam String email, @RequestParam String mobileNumber, @RequestParam String username
            , @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        n.setMobileNumber(mobileNumber);
        n.setUsername(username);
        n.setPassword(password);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users

        return userRepository.findAll();
    }

    @GetMapping("/user/{username}/{password}")
    public @ResponseBody Iterable<User> getUserByCredentials(
            @PathVariable String username,
            @PathVariable String password) {

        return userRepository.findByUsernameAndPassword(username, password);
    }
}