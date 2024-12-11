<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QuickBite - Registration</title>
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
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
        }

        .registration-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }

        .registration-box {
            background-color: white;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .registration-box h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .registration-box label {
            display: block;
            margin-bottom: 5px;
            font-size: 1em;
            color: #555;
        }

        .registration-box input[type="text"],
        .registration-box input[type="email"],
        .registration-box input[type="password"],
        .registration-box input[type="textarea"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
        }

        .registration-box input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #ff7f50; /* Primary button color */
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .registration-box input[type="submit"]:hover {
            background-color: #e76838; /* Darker shade on hover */
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #333;
            color: white;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>QuickBite</h1>
    </header>

    <div class="registration-container">
        <div class="registration-box">
            <h2>Register</h2>
            <form action="register" method="post">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required placeholder="Your Name">

                <label for="email">Email</label>
                <input type="email" id="email" name="email" required placeholder="example@xyz.com">

                <label for="phoneNumber">Phone Number</label>
                <input type="text" id="phoneNumber" name="phoneNumber" required placeholder="Your Phone Number">

                <label for="password">Password</label>
                <input type="password" id="password" name="password" required placeholder="Your Password">

                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Confirm Password">

                <label for="address">Address</label>
                <input id="address" name="address" type="text" required placeholder="Your Address">

                <input type="submit" value="Sign Up">
            </form>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 QuickBite. All rights reserved.</p>
    </footer>
</body>
</html>
