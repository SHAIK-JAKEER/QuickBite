<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to QuickBite</title>
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
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            text-align: center;
        }

        header {
            background-color: #333;
            color: white;
            padding: 20px 0;
            width: 100%;
            position: fixed;
            top: 0;
            left: 0;
            text-align: center;
        }

        header img {
            width: 150px; /* Adjust the size as needed */
            margin-bottom: 10px;
        }

        h1 {
            margin: 20px 0;
            font-size: 2.5em;
            color: #ff7f50; /* Main color for headings */
        }

        a {
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
            background-color: #444;
            color: white;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #555;
        }

        .image-container {
            margin: 20px 0;
        }

        .welcome-image {
            max-width: 100%;
            height: auto;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <header>
        <img src="images/logo.jpg" alt="QuickBite Logo"> <!-- Replace with your logo path -->
        <h1>Welcome to QuickBite</h1>
    </header>

    <div class="image-container">
        <img class="welcome-image" src="images/welcome.jpg" alt="Welcome to QuickBite"> <!-- Replace with a relevant welcome image path -->
    </div>

    <div>
        <p>
            <a href="Register.jsp">Sign Up</a>
            <span style="margin: 0 10px;">|</span>
            <a href="Login.jsp">Login</a>
        </p>
    </div>
</body>
</html>
