<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Cart, java.util.Map, com.tap.model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping - Cart</title>
</head>
<body>
	<h1>Your Shopping Cart</h1>
	<div class="">
		<%
			Cart cart=(Cart)session.getAttribute("cart");
			if(cart!=null && !cart.getItems().isEmpty()) {
				for(CartItem item:cart.getItems().values()) {
		%>
		<div>
		<h3><%= item.getName() %></h3>
		
		<p>
			<%=item.getPrice() %> X <%=item.getQuantity()  %> = <%= item.getQuantity()*item.getPrice()%> </p>
			<form action="cart1">
				<input type="hidden" name="itemId" value ="<%= item.getItemId()%>">
				Quantity: <input type="number" name="quantity" value="<%=item.getQuantity() %>" min="1">
				<input type="hidden" name="action" value="update">
				<input type="submit" value="Update" >
				<input type="hidden" name="action" value="remove"> <br>
				<input type="submit" value="remove">
				<a href="Menu.jsp">Add more items </a> . <a href="home.jsp">Add more items from another Restaurant</a> <br>
				
				
				<% }} %>
				<br><input type="submit" value="proceed" name="proceed">
			</form>
		</div>
	</div>
</body>
</html>