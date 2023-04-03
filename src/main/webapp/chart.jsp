<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
int [] ages = new int[4];
ages = (int[]) request.getAttribute("ages");
String mass = Arrays.toString(ages);

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>График</title>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <title></title>
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
                            <a class="nav-link active" aria-current="page" href="getdata">Список сотрудников</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="profile?person=">Профиль</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="chart">Графики</a>
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
</header>
<body>
<div>
    <canvas id="myChart" style="display: block; box-sizing: border-box; height: 292px; width: 584px;" width="584" height="292"></canvas>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    let age = '<%=mass%>';
    let arr = JSON.parse("[" + age + "]");
    const ctx = document.getElementById('myChart');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['18-25', '26-30', '31-45', '45+'],
            datasets: [{
                label: 'Возраста',
                data: [arr[0][0], arr[0][1], arr[0][2], arr[0][3]],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false
        }
    });
</script>
</body>
</html>
