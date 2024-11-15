package com.hexaware.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.dto.UserDTO;
import com.hexaware.model.User;
import com.hexaware.repositories.UserRepository;
import com.hexaware.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user=modelMapper.map(userDTO,User.class);
//		Optional<User> existingUserByUsername = userRepo.findByName(user.getName());
        Optional<User> existingUserByEmail = userRepo.findByEmail(user.getEmail());
        Optional<User> existingUserByPhone = userRepo.findByNumber(user.getNumber());

        if (existingUserByEmail.isPresent() || existingUserByPhone.isPresent()) {
            throw new RuntimeException("Username, email, or phone number already exists. Please use different details.");
        }
		User saved=userRepo.save(user);
		return modelMapper.map(saved, UserDTO.class);
	}
	
//	@Override
//	public UserDTO updateuser(UserDTO userDTO, Integer userId) {
//        return updateUser(userId, userDTO);
//    }

	@Override
	public UserDTO login(String email, String password) {
	    User user = userRepo.findByEmail(email)
	            .filter(u -> u.getPassword().equals(password))
	            .orElseThrow(() -> new RuntimeException("Invalid email or password."));
	    
	    return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(int userId, UserDTO userDTO) {
        return userRepo.findById(userId)
                .map(user -> {
                    user.setName(userDTO.getName());
                    user.setGender(userDTO.getGender());
                    user.setCity(userDTO.getCity());
                    user.setState(userDTO.getState());
                    user.setNumber(userDTO.getNumber());
                    user.setEmail(userDTO.getEmail());
                    user.setRoles(userDTO.getRoles());
                    User updatedUser = userRepo.save(user);
                    return modelMapper.map(updatedUser, UserDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

	@Override
	public boolean updatePassword(int userId, String newPassword) {
        return userRepo.findById(userId)
                .map(user -> {
                    user.setPassword(newPassword);
                    userRepo.save(user);
                    return true;
                })
                .orElse(false);
    }

	@Override
	 public UserDTO getUserById(int userId) {
        return userRepo.findById(userId)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
	
	
	
	
}