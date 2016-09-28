<?php

set_time_limit(0);
//$number = trim($_POST["number"]);
//$time = trim($_POST["time"]);
//$text = trim($_POST["text"]);
$address = '127.0.0.1';
$port = 6789;
$sockets = array();
$number = 1;

if(!empty($number)) {
    $socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
    if ($socket === false) {
        echo "Не удалось инициализировать сокет: " . socket_strerror(socket_last_error()) . "\n";
    } else {
        echo "Инициализация сокета... OK.\n";
    }
    echo "Попытка подключения к '$address' : '$port'...";
    $result = socket_connect($socket, $address, $port);
    if ($result === false) {
        echo "Подключение не удалось: ($result) " . socket_strerror(socket_last_error($socket)) . "\n";
    } else {
        echo "Подключение... OK.\n";
    }
    $number = "Р956МЕ99";
    echo "Отправка номера...";
    socket_write($socket, $number, strlen($number));
    echo "Отправка произведена.\n";
    echo "Ожидание ответа:\n\n";
    while ($out = socket_read($socket, 2048)) {
        echo $out;
    }

    echo "Закрытие соединения...";
    socket_close($socket);
    echo "OK.\n\n";
}
//echo $number_bytes;
//$host="194.87.234.46:3306";/*Имя сервера*/
//$user="parkingdata";/*Имя пользователя*/
//$password="KBwfFg";/*Пароль пользователя*/
//$db="carsystem";/*Имя базы данных*/
//mysql_connect($host, $user, $password) or die("<p>Ошибка выбора базы данных! ". mysql_error() . "</p>");; /*Подключение к серверу. Если не подключился, то ошибка*/
//mysql_select_db($db); /*Подключение к базе данных на сервере*/
//$insert_sql = "INSERT INTO car_number(number)". "VALUES('{$number}');";
//mysqli_query($insert_sql);


?>