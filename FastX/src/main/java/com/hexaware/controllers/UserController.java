package com.hexaware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.UserDTO;
import com.hexaware.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
		UserDTO registerUser=userService.registerUser(userDTO);
		return new ResponseEntity<>(registerUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/login/{email}/{password}")
    public ResponseEntity<UserDTO> loginUser(@PathVariable String email, @PathVariable String password) {
        UserDTO userDTO = userService.login(email, password);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
	
	@PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
	
	@PutMapping("/updatePassword/{userId}")
	public ResponseEntity<String> changePassword(@PathVariable int userId, @RequestBody String newPassword) {
	    if (newPassword == null || newPassword.isEmpty()) {
	        return new ResponseEntity<>("New password is required.", HttpStatus.BAD_REQUEST);
	    }

	    boolean isUpdated = userService.updatePassword(userId, newPassword);
	    if (isUpdated) {
	        return new ResponseEntity<>("Password updated successfully.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}