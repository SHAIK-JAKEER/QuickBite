package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.DAO.MenuDAO;
import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.Menu;

@SuppressWarnings("serial")
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int restaurantId=Integer.parseInt(req.getParameter("restaurantId"));
		MenuDAO menuDAO=new MenuDAOImpl();
		List<Menu> menuList=(List<Menu>) menuDAO.fetchAll(restaurantId);
		
		HttpSession session=req.getSession();
		
		session.setAttribute("menuList", menuList);
		
		resp.sendRedirect("Menu.jsp");
}
}
