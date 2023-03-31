<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная</title>
</head>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Список</title>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<header>
    <%ArrayList<String> employers = (ArrayList<String>) (request.getAttribute("employers"));%>
    <div class="office-nav">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="getdata">Список сотрудников</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="profile?person=<%=employers.get(0)%>">Профиль</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="chart">Графики</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
<body>

<div class="list">
    <ul class="list-content">
        <%
            for(int i = 0; i < employers.toArray().length; i++){
               out.print("<li>" + "<a href='/Office-1.0-SNAPSHOT/profile?person="+employers.get(i)+"'>" + employers.get(i) + "</a>" + "</li>");
            }
        %>
    </ul>
</div>
</body>
</html>