package com.atmaluhur.mobisuts_2411500025.api

import com.atmaluhur.mobisuts_2411500025.model.PengumumanResponse

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
interface ApiService {
    @Multipart
    @POST("pendaftaran.php")
    fun daftarSiswa(
        @Part foto: MultipartBody.Part?,
        @Part("nama_lengkap") nama: RequestBody,
        @Part("tempat_lahir") tempat: RequestBody,
        @Part("tanggal_lahir") tgl: RequestBody,
        @Part("nomor_wa") wa: RequestBody,
        @Part("asal_sekolah") sekolah: RequestBody,
        @Part("alamat_lengkap") alamat: RequestBody
    ): Call<ResponseBody>

    @GET("get_pengumuman.php")
    fun getPengumuman(): Call<List<PengumumanResponse>>
}