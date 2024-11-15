package com.hexaware.services;

import org.springframework.stereotype.Service;

import com.hexaware.dto.UserDTO;
@Service
public interface UserService {
//	public String adduser(User user);
//	UserDTO updateuser(UserDTO user,Integer userid);
	UserDTO registerUser(UserDTO userDTO);

    UserDTO login(String email, String password);

    UserDTO updateUser(int userId, UserDTO userDTO);

    boolean updatePassword(int userId, String newPassword);

    //common
    UserDTO getUserById(int userId);
    
    //admin
    ///login,getall,deleteusers,*(flag)

}