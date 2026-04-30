package com.atmaluhur.mobisuts_2411500025;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailPengumumanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengumuman);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detail Pengumuman");
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ImageView imgDetail = findViewById(R.id.img_detail);
        TextView tvJudul = findViewById(R.id.tv_detail_judul);
        TextView tvTanggal = findViewById(R.id.tv_detail_tanggal);
        TextView tvDeskripsi = findViewById(R.id.tv_detail_deskripsi);

        Pengumuman pengumuman = (Pengumuman) getIntent().getSerializableExtra("DATA_PENGUMUMAN");

        if (pengumuman != null) {
            imgDetail.setImageResource(pengumuman.getGambar());
            tvJudul.setText(pengumuman.getJudul());
            tvTanggal.setText(pengumuman.getTanggal());
            tvDeskripsi.setText(pengumuman.getDeskripsi());
        }
    }
}
