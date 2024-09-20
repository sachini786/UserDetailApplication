package com.example.UserDetail.controller;

import com.example.UserDetail.exception.ResourceNotFondException;
import com.example.UserDetail.model.User;
import com.example.UserDetail.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/User")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @PatchMapping
    public User updateUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("{UserID}")
    public ResponseEntity<User> getUserById(@PathVariable Integer UserID) {
        System.out.println("Requested UserID: " + UserID); // Log the requested ID
        User user = userRepository.findById(UserID)
                .orElseThrow(() -> new ResourceNotFondException("User not exist with id " + UserID));
        System.out.println("hello sachini"); // Log the retrieved user
        return ResponseEntity.ok(user);
    }

    @PostMapping("/User")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {

        // Validate mandatory fields
        if (user.getFirstName() == null ||user.getLastName()==null || user.getDateOfBirth() == null ||user.getEmail()==null ||
                user.getAddress() == null || user.getPhoneNumber() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: First name,last name , Date of Birth, Address,Email  and Phone number are mandatory fields.");
        }

        if (user.getEmail() != null && userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Error: Email is already in use!");
        }
        if (result.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder("Validation errors: ");
            result.getFieldErrors().forEach(error -> {
                errorMsg.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(". ");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg.toString());
        }

        try {
            // Validate if date is correct
            LocalDate dateOfBirth = user.getDateOfBirth();  // This will throw DateTimeParseException if invalid date
            if (dateOfBirth.isAfter(LocalDate.now())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Error: Date of birth cannot be in the future");
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Invalid date format. Please use dd/MM/yyyy");
        }

        // Save the user if all validations pass
        User savedUser = userRepository.save(user);
        System.out.println(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("{UserID}")
    public ResponseEntity <User> updateUser(@PathVariable Integer UserID,@RequestBody User UserDetails){
        User updateUser = userRepository.findById(UserID)
                .orElseThrow(()->new ResourceNotFondException("User not exist id" +UserID));
        updateUser.setFirstName(UserDetails.getFirstName());
        updateUser.setLastName(UserDetails.getLastName());
        updateUser.setDateOfBirth(UserDetails.getDateOfBirth());
        updateUser.setAddress(UserDetails.getAddress());
        updateUser.setAge(UserDetails.getAge());
        updateUser.setEmail(UserDetails.getEmail());
        updateUser.setEmail(UserDetails.getEmail());

        userRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{UserID}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer UserID){
        User user = userRepository.findById(UserID)
                .orElseThrow(()->new ResourceNotFondException("User not exist id" +UserID));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
