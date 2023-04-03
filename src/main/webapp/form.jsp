<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.04.2023
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить работника</title>
</head>
<body>
<form action="create" method="post">
    <label for="username">Логин:</label>
    <input type="text" id="username" name="username"><br><br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
