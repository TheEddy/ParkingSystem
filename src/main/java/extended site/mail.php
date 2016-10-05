<?php

set_time_limit(0);
$number = trim($_GET["number"]);
$number = $number . PHP_EOL;
$time = trim($_GET["time"]);
$address = "localhost";
$port = 6789;
$sockets = array();
$errstr = '';
$errno = '';


if(!empty($number)) {
  print($number);
  $socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
  if ($socket === false) {
    echo "Не удалось инициализировать сокет: " . socket_strerror(socket_last_error()) . "\n";
  } else {
    echo "Инициализация сокета... OK.\n";
  }
  echo "Попытка подключения к $address:$port...";
  $result = socket_connect($socket, $address, $port);
  if ($result === false) {
    echo "Подключение не удалось: ($result) " . socket_strerror(socket_last_error($socket)) . "\n";
  } else {
    echo "Подключение... OK.\n";
  }
  echo "Отправка номера...\n";
  echo $number;
  echo "\n";
  socket_write($socket, $number, strlen($number));
  echo "Отправка произведена.\n";
  echo "Ожидание ответа:\n\n";
  while ($out = socket_read($socket, 2048)) {
    echo $out;
  }

  echo "\nЗакрытие соединения...";
  socket_close($socket);
  echo "OK.\n\n";
}
?>