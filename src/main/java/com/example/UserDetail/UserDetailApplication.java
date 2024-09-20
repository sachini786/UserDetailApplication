package com.example.UserDetail;

import com.example.UserDetail.model.User;
import com.example.UserDetail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class UserDetailApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserDetailApplication.class, args);
	}
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

	}

}
