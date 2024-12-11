<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.tap.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QuickBite - Restaurants Near You</title>
    <style>
        /* Global reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
            color: #333;
        }

        header {
            background-color: #333;
            color: white;
            padding: 10px 0;
        }

        header h1 {
            text-align: center;
            font-size: 2em;
            margin-bottom: 10px;
        }

        nav.nav {
            display: flex;
            justify-content: flex-end;
            gap: 15px;
            padding: 10px;
            background-color: #444;
        }

        nav.nav a {
            color: white;
            text-decoration: none;
            padding: 5px 10px;
            transition: background-color 0.3s ease;
        }

        nav.nav a:hover {
            background-color: #555;
            border-radius: 5px;
        }

        .box {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            margin: 20px auto;
            padding: 20px;
            max-width: 1200px;
            gap: 20px;
        }

        .restaurant {
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            width: 270px;
            transition: transform 0.3s ease;
        }

        .restaurant img {
            width: 100%;
            height: 200px;
            border-radius: 10px;
            object-fit: cover;
        }

        .restaurant h3 {
            font-size: 1.2em;
            margin-top: 10px;
        }

        .restaurant p {
            font-size: 0.9em;
            color: #555;
            margin: 5px 0;
        }

        .restaurant a {
            display: inline-block;
            padding: 5px 10px;
            background-color: #ff7f50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        .restaurant a:hover {
            background-color: #ff5733;
        }

        .restaurant:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #333;
            color: white;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to QuickBite</h1>
        <nav class="nav">
            <a href="home">Home</a>
            <%
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
            %>
                <span>Welcome, <%= loggedInUser.getUserName() %>!</span>
                <a href="cart">Cart</a>
                <a href="orderHistory">Order History</a>
                <a href="logout">Logout</a>
            <% } else { %>
                <a href="Register.jsp">SignUp</a>
                <a href="Login.jsp">Login</a>
            <% } %>
        </nav>
    </header>

    <h2 style="text-align: center; margin: 20px 0;">Restaurants Near You</h2>

    <div class="box">
        <%
        List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
        if (restaurantList != null) {
            for (Restaurant restaurant : restaurantList) {
        %>
            <div class="restaurant">
                <img alt="<%= restaurant.getRestaurantName() %> image" src="images/<%= restaurant.getImgPath() %>">
                <h3><%= restaurant.getRestaurantName() %></h3>
                <p>Rating: <%= restaurant.getRatings() %></p>
                <p><%= restaurant.getCuisineType() %> - <%= restaurant.getDeliveryTime() %> mins</p>
                <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>">View Menu</a>
            </div>
        <%
            }
        }
        %>
    </div>

    <footer>
        <p>&copy; 2024 QuickBite. All rights reserved.</p>
    </footer>
</body>
</html>
