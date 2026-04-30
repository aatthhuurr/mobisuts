package com.atmaluhur.mobisuts_2411500025;

import java.io.Serializable;

public class Pengumuman implements Serializable {
    private String judul;
    private String tanggal;
    private String deskripsi;
    private int gambar;

    public Pengumuman(String judul, String tanggal, String deskripsi, int gambar) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    public String getJudul() { return judul; }
    public String getTanggal() { return tanggal; }
    public String getDeskripsi() { return deskripsi; }
    public int getGambar() { return gambar; }
}
