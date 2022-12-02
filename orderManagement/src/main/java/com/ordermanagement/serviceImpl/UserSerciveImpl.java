package com.ordermanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.dao.UserDao;
import com.ordermanagement.dto.UserDto;
import com.ordermanagement.model.Users;
import com.ordermanagement.service.UserService;


@Service
public class UserSerciveImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public String saveUser(UserDto userDto) {
		int max = 10000;
		int min = 20000;
		Long p = (long) (Math.random() * (max - min + 1) + min);
		Users users = new Users();
		Optional<Users> id =userDao.findByUserId(userDto.getUserId());
		if (!id.isPresent()) {
			users.setUserId(p);
		}
		else {
			return "UserID is already available";
		}
		
		Optional<Users> email = Optional.ofNullable(userDao.findByEmail(userDto.getEmail()));
		if (!email.isPresent()) {
			users.setEmail(userDto.getEmail());
		}else {
			return "Email is already used";		
		}

		Optional<Users> contact = userDao.findByContact(userDto.getContact());
		if (!contact.isPresent()) {
			users.setPersoneName(userDto.getPersoneName());
			users.setAddress(userDto.getAddress());
			users.setContact(userDto.getContact());
			users.setEmail(userDto.getEmail());
			users.setPassword(userDto.getPassword());
			userDao.save(users);
			return "User Registration Successfully";

		} else {
			return "Contact Already registered";
		}
	}

	@Override
	public String validateUser(String email, String password) {
		Users users =userDao.findByEmail(email);
		if (password.equals(users.getPassword())) {
			return "Login Successful...";
		} else {
			return "Login Failed...";
		}
	}		
//to get all data
	@Override
	public List<Users> allUsers() {
		return userDao.findAll();
	}
//to delete 
	@Override
	public String userDelete(Long id) {
		userDao.deleteById(id);
		return  "User Deleted Successfully...";
	}

	@Override
	public String updateUser(Long userId, UserDto userDto) {
		Optional<Users> id1 = userDao.findByUserId(userId);
		if (id1.isPresent()) {
			Users users= userDao.getByUserId(userId);
			users.setAddress(userDto.getAddress());
			users.setPersoneName(userDto.getPersoneName());
			users.setPassword(userDto.getPassword());
			userDao.save(users);
			return "User Data updated Successfully...";
		} else {
			return "User Not Exist...";
		}
	}
}
