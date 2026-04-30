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

import com.atmaluhur.mobisuts_2411500025.api.RetrofitClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                        imgFoto.setImageTintList(null);

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

        if (etTanggalLahir != null) {
            etTanggalLahir.setOnClickListener(v -> showDatePicker());
        }

        if (btnDaftar != null) {
            btnDaftar.setOnClickListener(v -> {
                String nama = etNama.getText().toString().trim();
                String tempat = etTempatLahir.getText().toString().trim();
                String tanggal = etTanggalLahir.getText().toString().trim();
                String wa = etWa.getText().toString().trim();
                String sekolah = etAsalSekolah.getText().toString().trim();
                String alamat = etAlamat.getText().toString().trim();

                if (nama.isEmpty() || tempat.isEmpty() || tanggal.isEmpty() ||
                        wa.isEmpty() || sekolah.isEmpty() || alamat.isEmpty()) {

                    Toast.makeText(getContext(), "Semua data wajib diisi!", Toast.LENGTH_SHORT).show();

                    if (nama.isEmpty()) etNama.setError("Nama wajib diisi");
                    if (tempat.isEmpty()) etTempatLahir.setError("Tempat lahir wajib diisi");
                    if (tanggal.isEmpty()) etTanggalLahir.setError("Tanggal lahir wajib diisi");
                    if (wa.isEmpty()) etWa.setError("Nomor WA wajib diisi");
                    if (sekolah.isEmpty()) etAsalSekolah.setError("Asal sekolah wajib diisi");
                    if (alamat.isEmpty()) etAlamat.setError("Alamat wajib diisi");

                    return;
                }

                if (imageUri == null) {
                    Toast.makeText(getContext(), "Foto profil wajib diunggah!", Toast.LENGTH_SHORT).show();
                    return;
                }

                MultipartBody.Part partFoto = prepareFilePart("foto", imageUri);

                RetrofitClient.getInstance().daftarSiswa(
                        partFoto,
                        createPartFromString(nama),
                        createPartFromString(tempat),
                        createPartFromString(tanggal),
                        createPartFromString(wa),
                        createPartFromString(sekolah),
                        createPartFromString(alamat)
                ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(requireContext(), "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show();

                            new com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                                    .setTitle("Berhasil")
                                    .setMessage("Data pendaftaran Anda telah tersimpan!")
                                    .setPositiveButton("Okeh", (dialog, which) -> {
                                        etNama.setText("");
                                        etTempatLahir.setText("");
                                        etTanggalLahir.setText("");
                                        etWa.setText("");
                                        etAsalSekolah.setText("");
                                        etAlamat.setText("");
                                        imgFoto.setImageResource(R.drawable.ic_pendaftaran);
                                        imgFoto.setPadding(40, 40, 40, 40);
                                        imageUri = null;
                                    })
                                    .show();
                        } else {
                            Toast.makeText(requireContext(), "Gagal: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(requireContext(), "Kesalahan Koneksi: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
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
                    String selectedDate = yearSelected + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    etTanggalLahir.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    private RequestBody createPartFromString(String stringData) {
        return RequestBody.create(MultipartBody.FORM, stringData);
    }

    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(fileUri);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);

            RequestBody requestFile = RequestBody.create(
                    MediaType.parse(requireContext().getContentResolver().getType(fileUri)),
                    bytes
            );

            return MultipartBody.Part.createFormData(partName, "foto.jpg", requestFile);
        } catch (Exception e) {
            return null;
        }
    }
}