package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.DAO.UserDAO;
import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;

@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		
		UserDAO userDAO=new UserDAOImpl();
	
		User user=new User();
		
		user.setUserName(userName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setAddress(address);
		
		int status=userDAO.insertUser(user);
		
		if(status!=0) {
			resp.sendRedirect("home.jsp");
		}
		else {
			resp.sendRedirect("failure.jsp");
		}
		
	}

}
