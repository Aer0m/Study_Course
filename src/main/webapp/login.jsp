<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<form action="login" method="post">
    <label for="username">Логин:</label>
    <input type="text" id="username" name="username"><br><br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Войти">
</form>
</body>
</html>