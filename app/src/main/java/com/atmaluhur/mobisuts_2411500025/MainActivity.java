package com.atmaluhur.mobisuts_2411500025;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etNama, etTempatLahir, etTanggalLahir, etWa, etAsalSekolah, etAlamat;
    private MaterialButton btnDaftar, btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Pastikan layout yang di-set adalah yang berisi ID et_nama, dll.
        // Jika formulir ada di fragment_pendaftaran, pastikan activity_main memuatnya.
        setContentView(R.layout.activity_main);

        // PERBAIKAN: Menggunakan ID 'main' untuk setPadding agar tidak menabrak drawer
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Inisialisasi View
        etNama = findViewById(R.id.et_nama);
        etTempatLahir = findViewById(R.id.et_tempat_lahir);
        etTanggalLahir = findViewById(R.id.et_tanggal_lahir);
        etWa = findViewById(R.id.et_wa);
        etAsalSekolah = findViewById(R.id.et_asal_sekolah);
        etAlamat = findViewById(R.id.et_alamat);
        btnDaftar = findViewById(R.id.btn_daftar);
        btnUpload = findViewById(R.id.btn_upload);

        // Date Picker untuk Tanggal Lahir
        if (etTanggalLahir != null) {
            etTanggalLahir.setOnClickListener(v -> showDatePicker());
        }

        if (btnDaftar != null) {
            btnDaftar.setOnClickListener(v -> {
                if (etNama != null && etNama.getText().toString().isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong");
                } else {
                    Toast.makeText(MainActivity.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (btnUpload != null) {
            btnUpload.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "Fitur Unggah Foto", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, yearSelected, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + yearSelected;
                    etTanggalLahir.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    public TextInputEditText getEtTempatLahir() {
        return etTempatLahir;
    }

    public void setEtTempatLahir(TextInputEditText etTempatLahir) {
        this.etTempatLahir = etTempatLahir;
    }

    public TextInputEditText getEtWa() {
        return etWa;
    }

    public void setEtWa(TextInputEditText etWa) {
        this.etWa = etWa;
    }
}