package com.hexaware.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.dto.UserDTO;
import com.hexaware.exceptions.EmailAlreadyExistsException;
import com.hexaware.exceptions.PhoneAlreadyExistsException;
import com.hexaware.exceptions.UserNotFoundException;
import com.hexaware.model.Users;
import com.hexaware.repositories.UserRepository;
import com.hexaware.services.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public UserDTO registerUser(UserDTO userDTO) throws PhoneAlreadyExistsException, EmailAlreadyExistsException {
		Users user=modelMapper.map(userDTO,Users.class);
//		Optional<User> existingUserByUsername = userRepo.findByName(user.getName());
        Users existingUserByEmail = userRepo.findByEmail(user.getEmail());
        Users existingUserByPhone = userRepo.findByNumber(user.getNumber());

        if (existingUserByEmail!=null) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        if(existingUserByPhone!=null) {
        	throw new PhoneAlreadyExistsException("Phone Number already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		Users saved=userRepo.save(user);
		return modelMapper.map(saved, UserDTO.class);
	}
	

	@Override
	public UserDTO updateUser(String email, UserDTO userDTO) throws UserNotFoundException {
	    Users user = userRepo.findByEmail(email);
	    if (user == null) {
	        throw new UserNotFoundException("User not found: " + email);
	    }

	    if (userDTO.getName() != null) {
	        user.setName(userDTO.getName());
	    }
	    if (userDTO.getGender() != null) {
	        user.setGender(userDTO.getGender());
	    }
	    if (userDTO.getCity() != null) {
	        user.setCity(userDTO.getCity());
	    }
	    if (userDTO.getState() != null) {
	        user.setState(userDTO.getState());
	    }
	    if (userDTO.getNumber() != null) {
	        user.setNumber(userDTO.getNumber());
	    }
	    
	    if(userDTO.getDob() !=null) {
	    	user.setDob(userDTO.getDob());
	    }
	     if(userDTO.getAddress()!=null) {
	    	 user.setAddress(userDTO.getAddress());
	     }
	     if(userDTO.getCountry()!=null) {
	    	 user.setCountry(userDTO.getCountry());
	     }
	    

	    Users updatedUser = userRepo.save(user);
	    return modelMapper.map(updatedUser, UserDTO.class);
	}


	@Override
	public boolean updatePassword(String email,String password, String newPassword) throws UserNotFoundException {
		Users user =userRepo.findByEmail(email);
		if(user!=null) {
			if(passwordEncoder.matches(password, user.getPassword())) {
				user.setPassword(passwordEncoder.encode(newPassword));
	            userRepo.save(user);
	            return true;
			}
		}
		else
			throw new UserNotFoundException("User Not Found "+email);
		return false;
    }

	@Override
	 public UserDTO getUserById(String email) throws UserNotFoundException {
		Users user =userRepo.findByEmail(email);
		if(user!=null)
			return modelMapper.map(user, UserDTO.class);
		else throw new UserNotFoundException("User not found "+email);
    }

	//admin
	@Override
	public List<UserDTO> getAllUsers() {
	    List<Users> users = userRepo.findAll();
	    List<UserDTO> userDTOs = new ArrayList<>();

	    for (Users user : users) {
	        userDTOs.add(modelMapper.map(user, UserDTO.class));
	    }

	    return userDTOs;
	}

    @Override
    public void deleteUser(String email) throws UserNotFoundException {
    	Users user =userRepo.findByEmail(email);
        if (user!=null) {
            userRepo.delete(user);;
        } else {
            throw new UserNotFoundException("User not found "+email);
        }
    }


	@Override
	public String sendOtp(String reason, String email, Integer otp) {
	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        String name = email.split("@")[0]; // Extract name from email address
	        
	        helper.setFrom("mr4speedster@gmail.com");
	        helper.setTo(email);
	        
	        if (reason.equalsIgnoreCase("signup")) {
	            helper.setSubject("Welcome to FastX - OTP for Account Registration");
	            helper.setText(String.format(
	                "<html><body>"
	                + "Dear <b>%s</b>,<br><br>"
	                + "Thank you for choosing FastX for your travel needs. To complete your account registration, "
	                + "please use the following One Time Password (OTP): <b>%s</b><br><br>"
	                + "If you did not initiate this request, please ignore this email or contact our support team immediately.<br><br>"
	                + "Best Regards,<br>"
	                + "FastX Team"
	                + "</body></html>",
	                name, otp
	            ), true);
	        } else if (reason.equalsIgnoreCase("recovery")) {
	            helper.setSubject("FastX Account Recovery - OTP");
	            helper.setText(String.format(
	                "<html><body>"
	                + "Dear <b>%s</b>,<br><br>"
	                + "We received a request to recover your FastX account. To proceed, please use the following One Time Password (OTP): <b>%s</b><br><br>"
	                + "If you did not initiate this request, we recommend securing your account and contacting our support team immediately.<br><br>"
	                + "Best Regards,<br>"
	                + "FastX Support Team"
	                + "</body></html>",
	                name, otp
	            ), true);
	        } else {
	            return "Invalid reason provided for sending OTP.";
	        }
	        
	        mailSender.send(mimeMessage);
	        return "success";
	    } catch (MessagingException e) {
	        return "Failed to send email: " + e.getMessage();
	    }
	}




	@Override
	public String forgetPassword(String email, String newPassword) throws UserNotFoundException {
		Users user=userRepo.findByEmail(email);
		if(user==null)
			throw new UserNotFoundException("User not found: "+email);
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepo.save(user);
		
		return "success";
		
	}
	
	
	
}
