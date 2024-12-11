package com.tap.DAO;

import java.util.List;

import com.tap.model.User;

public interface UserDAO {
	int insertUser(User u);
	int deleteUser(String email);
	List <User> fetchAll();
	User fetchUser(String email);
	int updateUser(User u);
}
