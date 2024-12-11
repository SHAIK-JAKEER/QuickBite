package com.tap.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.UserDAO;
import com.tap.DBUtils.DBUtils;
import com.tap.model.User;

public class UserDAOImpl implements UserDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private int status;
	private Statement stmt;
	private ResultSet resultSet;
	private User user;
	
	ArrayList<User> userList=new ArrayList<User>();
	
	private final String INSERT_USER="insert into `user`(`userName`,`email`, `phonenumber`,`password`,`address`) values (?,?,?,?,?)";
	private final String DELETE_USER="delete from `user` where `email`=?";
	private final String FETCH_ALL_USERS="select * from user";
	private final String FETCH_ON_EMAIL="select * from user where `email`=?";
	private final String UPDATE_USER_ON_EMAIL="update `user` set `userName`=?, `phonenumber`=?,`password`=?,`address`=? where `email`=?";

	
	public UserDAOImpl() {
		con=DBUtils.connect();
	}

	
	@Override
	public int insertUser(User u) {
		try {
			pstmt=con.prepareStatement(INSERT_USER);
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPhoneNumber());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getAddress());
			
			status=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteUser(String email) {
		try {
			pstmt=con.prepareStatement(DELETE_USER);
			
			pstmt.setString(1, email);
			
			status=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<User> fetchAll() {
		try {
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCH_ALL_USERS);
			
			userList=extractUserFromResultSet(resultSet);
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
}

	
	@Override
	public int updateUser(User u){
		try {
			pstmt=con.prepareStatement(UPDATE_USER_ON_EMAIL);
			
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getPhoneNumber());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(5, u.getEmail());

			status=pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	

	
	
	
	
	 ArrayList<User> extractUserFromResultSet(ResultSet resultSet) {
		 try {
		while(resultSet.next()) {
			userList.add(new User(resultSet.getInt("userId"),
			resultSet.getString("userName"),
			resultSet.getString("email"),
			resultSet.getString("phoneNumber"),
			resultSet.getString("password"),
			resultSet.getString("address")
			));
		}
		 }
		catch(Exception e){
			e.printStackTrace();	
	}
		 return userList;

}


	@Override
	public User fetchUser(String email) {
		try {
			
			pstmt=con.prepareStatement(FETCH_ON_EMAIL);
			
			pstmt.setString(1, email);
			resultSet=pstmt.executeQuery();
			
			userList=extractUserFromResultSet(resultSet);
			if (!userList.isEmpty()) {
	            user = userList.get(0);
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
