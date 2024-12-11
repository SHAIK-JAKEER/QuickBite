package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.DAO.RestaurantDAO;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

@SuppressWarnings("serial")
@WebServlet("/home")
public class Home extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAO resturantDAO = new RestaurantDAOImpl();
		List<Restaurant> restaurantList=resturantDAO.fetchAll();
		HttpSession session=req.getSession();
		
		session.setAttribute("restaurantList", restaurantList);
		
		resp.sendRedirect("home.jsp");
	}
}