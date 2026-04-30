<?php
include 'koneksi.php';

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $nama    = isset($_POST['nama_lengkap']) ? $_POST['nama_lengkap'] : '';
    $tempat  = isset($_POST['tempat_lahir']) ? $_POST['tempat_lahir'] : '';
    $tanggal = isset($_POST['tanggal_lahir']) ? $_POST['tanggal_lahir'] : '';
    $wa      = isset($_POST['nomor_wa']) ? $_POST['nomor_wa'] : '';
    $sekolah = isset($_POST['asal_sekolah']) ? $_POST['asal_sekolah'] : '';
    $alamat  = isset($_POST['alamat_lengkap']) ? $_POST['alamat_lengkap'] : '';

    $nama_file_db = ""; 
    
    if (isset($_FILES['foto']) && $_FILES['foto']['error'] == 0) {
        $target_dir  = "uploads/";
        $extension   = pathinfo($_FILES["foto"]["name"], PATHINFO_EXTENSION);
        
        $nama_file_baru = "pendaftar_" . time() . "." . $extension;
        $target_file    = $target_dir . $nama_file_baru;

        if (move_uploaded_file($_FILES["foto"]["tmp_name"], $target_file)) {
            $nama_file_db = $nama_file_baru;
        }
    }

    $sql = "INSERT INTO tpendaftaran (foto, nama_lengkap, tempat_lahir, tanggal_lahir, nomor_wa, asal_sekolah, alamat_lengkap) 
            VALUES ('$nama_file_db', '$nama', '$tempat', '$tanggal', '$wa', '$sekolah', '$alamat')";

    if (mysqli_query($conn, $sql)) {
        echo json_encode([
            "status"  => "success",
            "message" => "Pendaftaran berhasil disimpan!"
        ]);
    } else {
        echo json_encode([
            "status"  => "error",
            "message" => "Gagal menyimpan ke database: " . mysqli_error($conn)
        ]);
    }

} else {
    echo json_encode([
        "status"  => "error",
        "message" => "Metode akses tidak diizinkan."
    ]);
}

mysqli_close($conn);
?>