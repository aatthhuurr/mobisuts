package com.atmaluhur.mobisuts_2411500025;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class PendaftaranFragment extends Fragment {

    private TextInputEditText etNama, etTempatLahir, etTanggalLahir, etWa, etAsalSekolah, etAlamat;
    private MaterialButton btnDaftar, btnUpload;
    private ImageView imgFoto;
    private Uri imageUri;

    private final ActivityResultLauncher<Intent> photoPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    if (imgFoto != null && imageUri != null) {
                        imgFoto.setPadding(0, 0, 0, 0);
                        imgFoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgFoto.setImageURI(imageUri);
                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pendaftaran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi View
        etNama = view.findViewById(R.id.et_nama);
        etTempatLahir = view.findViewById(R.id.et_tempat_lahir);
        etTanggalLahir = view.findViewById(R.id.et_tanggal_lahir);
        etWa = view.findViewById(R.id.et_wa);
        etAsalSekolah = view.findViewById(R.id.et_asal_sekolah);
        etAlamat = view.findViewById(R.id.et_alamat);
        btnDaftar = view.findViewById(R.id.btn_daftar);
        btnUpload = view.findViewById(R.id.btn_upload);
        imgFoto = view.findViewById(R.id.img_foto);

        // Date Picker untuk Tanggal Lahir
        if (etTanggalLahir != null) {
            etTanggalLahir.setOnClickListener(v -> showDatePicker());
        }

        if (btnDaftar != null) {
            btnDaftar.setOnClickListener(v -> {
                if (etNama != null && etNama.getText().toString().isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong");
                } else {
                    Toast.makeText(getContext(), "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (btnUpload != null) {
            btnUpload.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerLauncher.launch(intent);
            });
        }
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, yearSelected, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + yearSelected;
                    etTanggalLahir.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }
}
