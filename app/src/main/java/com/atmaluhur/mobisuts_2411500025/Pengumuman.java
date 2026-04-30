package com.atmaluhur.mobisuts_2411500025;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Pengumuman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_pengumuman); // Sesuai nama file XML kamu

        // 1. Inisialisasi View sesuai ID di XML terbaru
        ImageView imgFoto = findViewById(R.id.img_detail_foto);
        TextView tvJudul = findViewById(R.id.tv_detail_judul);
        TextView tvTanggal = findViewById(R.id.tv_detail_tanggal);
        TextView tvDeskripsi = findViewById(R.id.tv_detail_deskripsi);

        // 2. Ambil data dari Intent (dikirim oleh Adapter)
        String judul = getIntent().getStringExtra("JUDUL");
        String tanggal = getIntent().getStringExtra("TANGGAL");
        String deskripsi = getIntent().getStringExtra("DESKRIPSI");
        String fotoUrl = getIntent().getStringExtra("FOTO");

        // 3. Set data ke View
        tvJudul.setText(judul);
        tvTanggal.setText(tanggal);
        tvDeskripsi.setText(deskripsi);

        // Load gambar menggunakan Glide
        Glide.with(this)
                .load(fotoUrl)
                .placeholder(R.drawable.logo_aplikasi)
                .into(imgFoto);

        // Set judul di Toolbar (opsional)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Info");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}