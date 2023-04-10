<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="person" scope="request" type="java.lang.String"/>
<jsp:useBean id="age" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="address" scope="request" type="java.lang.String"/>
<jsp:useBean id="schedule" scope="request" type="java.lang.String"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Профиль</title>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<header>
    <div class="office-nav">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="getdata">Список сотрудников</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link  active" href="profile">Профиль</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="chart">Графики</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Выход</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
<body>
<div class="profile">
    <p><img src="images/avatar.jpg" class="img-thumbnail  shadow p-3 mb-5 bg-body-tertiary rounded" alt="невозможно отобразить аватар"
            vspace="20" hspace="20"/>
    <div class="info">
        <p class="data">Имя: ${person}</p>
        <p class="data">Возраст: ${age}</p>
        <p class="data">Адрес: ${address}</p>
        <p class="data">График работы: ${schedule}</p>
    </div>
</div>
</body>
</html>