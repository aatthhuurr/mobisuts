<?php

$host     = "localhost";
$username = "root";     
$password = "";         
$database = "2411500025"; 

$conn = mysqli_connect($host, $username, $password, $database);

if (!$conn) {
    die(json_encode([
        "status"  => "error",
        "message" => "Koneksi Database Gagal: " . mysqli_connect_error()
    ]));
}

mysqli_set_charset($conn, "utf8");
?>