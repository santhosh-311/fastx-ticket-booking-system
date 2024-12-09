package com.hexaware.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.dto.UserDTO;
import com.hexaware.exceptions.EmailAlreadyExistsException;
import com.hexaware.exceptions.PhoneAlreadyExistsException;
import com.hexaware.exceptions.UserNotFoundException;

@Service
public interface UserService {
//	public String adduser(User user);
//	UserDTO updateuser(UserDTO user,Integer userid);
	UserDTO registerUser(UserDTO userDTO) throws PhoneAlreadyExistsException, EmailAlreadyExistsException;

    UserDTO updateUser(String email, UserDTO userDTO) throws UserNotFoundException;

    boolean updatePassword(String email, String password,String newPassword) throws UserNotFoundException;

    //common
    UserDTO getUserById(String email) throws UserNotFoundException;
    
    //admin
    List<UserDTO> getAllUsers();

    void deleteUser(String email) throws UserNotFoundException;
    
    String sendOtp(String reason,String email,Integer otp);
        
    String forgetPassword(String email,String newPassword) throws UserNotFoundException;

}
