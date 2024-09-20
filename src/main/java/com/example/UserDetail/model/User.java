package com.example.UserDetail.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import jakarta.validation.constraints.NotNull ;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email ;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userdetail")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID;

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must be a string and contain only letters")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = " last name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must be a string and contain only letters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull (message = " Date of Birth cannot be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull(message = "Email should be valid")
    @Email(message = "Email should be valid")
    @Column(name = "Email")
    private String email;

    @NotNull (message = "Address cannot be null")
    @Pattern(regexp = "^[\\p{L}0-9 ,.-]+$", message = "Address must be a valid string")
    @Column(name = "Address")
    private String address;

    @Column(name = "Age")
    @Min(value = 0, message = "Age must be a non-negative integer")
    private Integer age;

    @NotNull
    @Pattern(regexp="^\\+?[0-9. ()-]{7,25}$", message="Phone number should be valid")
    private String phoneNumber;
}
