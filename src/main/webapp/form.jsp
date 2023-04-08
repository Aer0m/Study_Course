<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить работника</title>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="create" method="post" accept-charset="UTF-8">
    <label for="fullname">ФИО:</label>
    <input type="text" id="fullname" name="fullname"><br><br>
    <label for="age">Возраст:</label>
    <input type="text" id="age" name="age"><br><br>
    <label for="county">Округ:</label>
    <select id="county" name="county">
        <option>ВАО</option>
        <option>ЗАО</option>
        <option>ЗелАО</option>
        <option>САО</option>
        <option>СВАО</option>
        <option>СЗАО</option>
        <option>ТИНАО</option>
        <option>ЦАО</option>
        <option>ЮАО</option>
        <option>ЮВАО</option>
        <option>ЮЗАО</option>
    </select><br><br>
    <label for="neigh">Район:</label>
    <input type="text" id="neigh" name="neigh"><br><br>
    <label for="address">Адрес:</label>
    <input type="text" id="address" name="address"><br><br>
    <label for="begintime">Начало рабочего дня:</label>
    <input type="time" id="begintime" name="begintime"><br><br>
    <label for="endtime">Конец рабочего дня:</label>
    <input type="time" id="endtime" name="endtime"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
