package com.hexaware.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.model.Users;
//import com.hexaware.model.User;
import com.hexaware.repositories.UserRepository;

@Service
public class UserDetailServiceConfig implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepo.findByEmail(username);
		if(user!=null) {
			return User.builder()
					.username(user.getEmail())
					.password(user.getPassword())
					.roles(user.getRoles()=="" || user.getRoles()==null ? "USER":user.getRoles())
					.build();
		}
		else {
			throw new UsernameNotFoundException(username);
		}
	}

}
