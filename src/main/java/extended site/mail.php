<?php

$number = trim($_POST["number"]);
$time = trim($_POST["time"]);
$text = trim($_POST["text"]);
$address = '127.0.0.1';
$port = 6789;
$sockets = array();

if(!empty($number)) {
  $number_bytes = utf8_encode($number);
  //resource socket_create ();
  if (($sock = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) === false) {
    echo "Не удалось создать соединение: " . socket_strerror(socket_last_error()) . "\n";
  }
  if (socket_bind($sock, $address, $port) === false) {
    echo "Не удалось установить адресс сервера: " . socket_strerror(socket_last_error($sock)) . "\n";
  }
  do {
    if (($msgsock = socket_accept($sock)) === false) {
      echo "socket_accept() failed: reason: " . socket_strerror(socket_last_error($sock)) . "\n";
      break;
    }
    while (true) ;

    socket_write($msgsock, $number, strlen($number));
    echo $number;
    socket_close($msgsock);
  } while (true);
//socket_close($sock);
}

else{
  echo '<script language="javascript">';
  echo 'alert("Введите в строку номер автомобиля.")';
  echo '</script>';
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