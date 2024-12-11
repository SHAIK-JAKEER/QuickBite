package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.DAO.UserDAO;
import com.tap.DAOImpl.UserDAOImpl;
import com.tap.model.User;


@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDAO userDAO=new UserDAOImpl();
		User user=userDAO.fetchUser(email);
		
		if (user == null) {
		    resp.getWriter().println("User not found.");
		}
		else if(password.equals(user.getPassword())){
			HttpSession session=req.getSession();
			session.setAttribute("loggedInUser", user);
			resp.sendRedirect("home");
		}
		else {
			req.setAttribute("errorMessage", "Invalid userName or Password");
			req.getRequestDispatcher("Login.jsp").forward(req,resp);
		}
	}
}
