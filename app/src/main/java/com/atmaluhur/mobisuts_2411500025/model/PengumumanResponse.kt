package com.atmaluhur.mobisuts_2411500025.model

import com.google.gson.annotations.SerializedName

data class PengumumanResponse(
    @SerializedName("ID_PENG")
    val idPeng: Int = 0,

    @SerializedName("CJDL_PENG")
    val judul: String = "",

    @SerializedName("DPOST")
    val tanggal: String = "",

    @SerializedName("CFOTO")
    val foto: String = "",

    @SerializedName("CDESKRIPSI")
    val deskripsi: String = ""
)