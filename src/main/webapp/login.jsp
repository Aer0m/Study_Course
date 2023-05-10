<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="stylesheet" type="text/css" href="css/loginstyle.css">
    <link rel="stylesheet" href="bootstrap/bootstrap.min.css">
</head>
<body>
<div class="testbox">
    <h1>Вход</h1>
    <form action="login" method="post">
        <hr>
        <input type="text" name="username" id="username" placeholder="Логин" required/>
        <input type="password" name="password" id="password" placeholder="Пароль" required/><br/>
        <button type="submit" class="btn btn-success">Success</button>
    </form>
</div>
</body>
</html>