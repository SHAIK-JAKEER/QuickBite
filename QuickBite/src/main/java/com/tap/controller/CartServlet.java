package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.tap.DAO.MenuDAO;
import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.model.CartItem;
import com.tap.model.Menu;
import com.tap.model.Cart;



@WebServlet("/cart1")
public class CartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		Cart cart=(Cart)session.getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		
		String action=req.getParameter("action");
		if("add".equals(action)) {
			
			addItemToCart(req, cart);
		}
		
		else if("update".equals(action)) {
			updateCartItem(req, cart);
		}
		
		else if("remove".equals(action)) {
			removeCartItem(req, cart);
		}
		
		session.setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");
	}

	private void removeCartItem(HttpServletRequest req, Cart cart) {
		int itemId=Integer.parseInt(req.getParameter("itemId"));
		
		cart.removeItem(itemId);
	}

	private void updateCartItem(HttpServletRequest req, Cart cart) {
		int itemId=Integer.parseInt(req.getParameter("itemId"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		
		cart.updateItem(itemId, quantity);
		
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		int itemId=Integer.parseInt(req.getParameter("itemId"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAO menuDAO=new MenuDAOImpl();
		Menu menuItem=menuDAO.fetchMenu(itemId);
		
		HttpSession session =req.getSession();
		session.setAttribute("restaurantId", menuItem.getRestaurantId());
		
		if(menuItem !=null) {
			CartItem item=new CartItem(menuItem.getMenuId(),
					menuItem.getRestaurantId(), 
					menuItem.getMenuName(),
					menuItem.getPrice(), 
					quantity);
			cart.addItem(item);
			
		}
		
		
		
		
		
		
		
		
		
	}
}
