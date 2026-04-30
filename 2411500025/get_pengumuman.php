<?php
include 'koneksi.php';

$query = "SELECT * FROM tpengumuman ORDER BY ID_PENG ASC";
$result = mysqli_query($conn, $query); 

$response = array();

if ($result) {
while($row = mysqli_fetch_assoc($result)) {
    
    $url_asli = $row['CFOTO'];
    
    $cari = "http://127.0.0.1/webmobisuts_1811500212/";
    $ganti = "http://192.168.100.125/2411500025/";
    
    $row['CFOTO'] = str_replace($cari, $ganti, $url_asli);
    
    $response[] = $row;
}}

header('Content-Type: application/json');
echo json_encode($response);
?>