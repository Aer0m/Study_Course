<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить работника</title>
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="create" method="post" accept-charset="UTF-8" class="form-valid">
    <label for="fullname">ФИО:</label>
    <input type="text" id="fullname" name="fullname" class="fullname field"><br><br>
    <label for="age">Возраст:</label>
    <input type="text" id="age" name="age" class="age field"><br><br>
    <label for="county">Округ:</label>
    <select id="county" name="county" class="county field">
        <option>ВАО</option>
        <option>ЗАО</option>
        <option>ЗелАО</option>
        <option>САО</option>
        <option>СВАО</option>
        <option>СЗАО</option>
        <option>ТиНАО</option>
        <option>ЦАО</option>
        <option>ЮАО</option>
        <option>ЮВАО</option>
        <option>ЮЗАО</option>
    </select><br><br>
    <label for="neigh">Район:</label>
    <input type="text" id="neigh" name="neigh" class="neigh field"><br><br>
    <label for="address">Адрес:</label>
    <input type="text" id="address" name="address" class="address field"><br><br>
    <label for="begintime">Начало рабочего дня:</label>
    <input type="time" id="begintime" name="begintime" class="begintime field"><br><br>
    <label for="endtime">Конец рабочего дня:</label>
    <input type="time" id="endtime" name="endtime" class="endtime field"><br><br>
    <input type="submit" value="Submit" class="validBtn">
</form>
<script lang="javascript">
    var validateBtn = document.querySelector('.validBtn');
    var form = document.querySelector('.form-valid');
    var fullname = document.querySelector('.fullname');
    var age = document.querySelector('.age');
    var county = document.querySelector('.county');
    var neigh = document.querySelector('.neigh');
    var address = document.querySelector('.address');
    var begintime = document.querySelector('.begintime');
    var endtime = document.querySelector('.endtime');

    form.addEventListener('submit', function () {
        //event.preventDefault(); //прерывает метод submit для просмотра значений полей формы
        console.log('clicked on validate');
        console.log('fullname: ', fullname.value);
        console.log('age: ', age.value);
        console.log('county: ', county.value);
        console.log('neighbourhood: ', neigh.value);
        console.log('address: ', address.value);
        console.log('begin time: ', begintime.value);
        console.log('end time: ', endtime.value);

        var errors = form.querySelectorAll('.error');
        for (var i = 0; i < errors.length; i++) {
            errors[i].remove();
        }

        var fields = form.querySelectorAll('.field');
        for (var i = 0; i < fields.length; i++) {
            if (!fields[i].value) {
                event.preventDefault();
                console.log('field is blank', fields[i]);
                var error = document.createElement('div');
                error.className='error';
                error.style.color = 'red';
                error.innerHTML = 'Поле не может быть пустым';
                form[i].parentElement.insertBefore(error, fields[i]);
            }
        }
    });
</script>
</body>
</html>
