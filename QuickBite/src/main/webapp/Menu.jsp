<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Menu" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QuickBite - Menu</title>
    <style>
        /* Global reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
        }

        header {
            background-color: #333;
            color: white;
            padding: 15px;
            text-align: center;
        }

        h1 {
            font-size: 2em;
            margin: 10px 0;
        }

        .menu-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            padding: 20px;
            max-width: 1200px;
            margin: auto;
        }

        .menu-item {
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            width: 270px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-align: center;
        }

        .menu-item img {
            width: 100%;
            height: 200px;
            border-radius: 10px;
            object-fit: cover;
        }

        .menu-item h3 {
            font-size: 1.2em;
            margin-top: 10px;
            color: #ff7f50;
        }

        .menu-item p {
            font-size: 1em;
            margin: 10px 0;
            color: #555;
        }

        .menu-item form {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }

        .menu-item input[type="number"] {
            width: 60px;
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }

        .menu-item input[type="submit"] {
            padding: 10px 15px;
            background-color: #ff7f50;
            border: none;
            color: white;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .menu-item input[type="submit"]:hover {
            background-color: #ff5733;
        }

        .menu-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        footer {
            text-align: center;
            padding: 20px;
            background-color: #333;
            color: white;
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <header>
        <h1>QuickBite - Menu</h1>
    </header>

    <div class="menu-container">
        <% 
        List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
        if (menuList != null) {
            for (Menu menu : menuList) { 
        %>
            <div class="menu-item">
                <img alt="<%= menu.getMenuName() %>" src="images/<%= menu.getImgPath() %>">
                <h3><%= menu.getMenuName() %></h3>
                <p><b>Price:</b> ₹<%= menu.getPrice() %></p>
                <form action="cart1" method="post">
                	<input type="hidden" name="itemId" value ="<%=menu.getMenuId()%>">
                	
                    <label for="quantity">Quantity:</label>
                    <input type="number" name="quantity" min="1" value="1">
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Add to Cart">
                </form>
            </div>
        <%
        }
        } else {
        %>
        <p>No menu items available.</p>
        <%
        }
        %>
    </div>

    <footer>
        <p>&copy; 2024 QuickBite. All rights reserved.</p>
    </footer>

</body>
</html>