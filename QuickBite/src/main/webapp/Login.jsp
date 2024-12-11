<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QuickBite - Login</title>
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

        .login-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }

        .login-box {
            background-color: white;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .login-box h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .login-box label {
            display: block;
            margin-bottom: 5px;
            font-size: 1em;
            color: #555;
        }

        .login-box input[type="text"], 
        .login-box input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
        }

        .login-box input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #ff7f50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-box input[type="submit"]:hover {
            background-color: #e76838;
        }

        .register-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            font-size: 0.9em;
            color: #333;
        }

        .register-link a {
            color: #ff7f50;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .register-link a:hover {
            color: #e76838;
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

    <div class="login-container">
        <div class="login-box">
            <h2>Login</h2>
            <form action="login" method="post">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" required>
                
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
                
                <input type="submit" value="Login">
            </form>

            <div class="register-link">
                <p>Don't have an account? <a href="Register.jsp">Sign Up</a></p>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 QuickBite. All rights reserved.</p>
    </footer>
</body>
</html>
