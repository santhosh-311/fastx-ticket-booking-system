package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.dto.AuthenticationReponse;
import com.hexaware.dto.UserDTO;
import com.hexaware.exceptions.EmailAlreadyExistsException;
import com.hexaware.exceptions.PhoneAlreadyExistsException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.model.Users;
import com.hexaware.repositories.UserRepository;
import com.hexaware.services.UserService;
import com.hexaware.webtoken.JwtService;
import com.hexaware.webtoken.LoginForm;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticateManager;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) throws PhoneAlreadyExistsException, EmailAlreadyExistsException, MethodArgumentNotValidException{
		UserDTO registerUser=userService.registerUser(userDTO);
		return new ResponseEntity<>(registerUser,HttpStatus.CREATED);
	}

	@PutMapping("/update/{userName}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userName, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        UserDTO updatedUser = userService.updateUser(userName, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
	
	@PutMapping("/updatepwd/{userName}/{password}/{newPassword}")
	public ResponseEntity<String> changePassword(@PathVariable String userName,@PathVariable String password, @PathVariable String newPassword) throws UserNotFoundException {
	    if (newPassword == null || newPassword.isEmpty()) {
	        return new ResponseEntity<>("New password is required.", HttpStatus.BAD_REQUEST);
	    }

	    boolean isUpdated = userService.updatePassword(userName,password, newPassword);
	    if (isUpdated) {
	        return new ResponseEntity<>("Password updated successfully.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Password does not match.",HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("/getuser/{userName}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userName) throws UserNotFoundException {
        UserDTO userDTO = userService.getUserById(userName);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
	
	@GetMapping("/admin/allusers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
	
	 @DeleteMapping("/admin/delete/{userName}")
	    public ResponseEntity<String> deleteUser(@PathVariable String userName) throws UserNotFoundException {
	            userService.deleteUser(userName);
	            return ResponseEntity.ok("User deleted successfully.");
	    }
	 
	 @PostMapping("/login")
		public ResponseEntity<AuthenticationReponse> login(@RequestBody LoginForm loginForm) {
			Authentication authentication =	authenticateManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginForm.username(),
							loginForm.password()
							)
					);
			if(authentication.isAuthenticated()) {
				AuthenticationReponse authResponse=new AuthenticationReponse();
				Users user=userRepo.findByEmail(loginForm.username());
				String jwt=jwtService.generateToken(userDetailService.loadUserByUsername(loginForm.username()));
				
				authResponse.setUserName(user.getEmail());
				authResponse.setRole(user.getRoles());
				authResponse.setJwt(jwt);
				return new ResponseEntity<AuthenticationReponse>(authResponse,HttpStatus.OK);
				
			}
			else {
				throw new UsernameNotFoundException("Invalid Login");
			}
		}
	 @PostMapping("/sendotp/{reason}/{userName}/{otp}")
	 public ResponseEntity<String> sendOtp(@PathVariable String reason, @PathVariable String userName,@PathVariable Integer otp ){
		 String s=userService.sendOtp(reason,userName, otp);
		 return new ResponseEntity<String>(s,HttpStatus.OK); 
	 }
	 
	 @PutMapping("/forgetpassword/{userName}/{password}")
	 public ResponseEntity<String> forgetPassword(@PathVariable String userName,@PathVariable String password) throws UserNotFoundException{
		 String s=userService.forgetPassword(userName, password);
		 return new ResponseEntity<String>(s,HttpStatus.OK);
	 }
}
