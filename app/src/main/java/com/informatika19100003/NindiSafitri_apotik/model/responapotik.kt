import com.google.gson.annotations.SerializedName

data class ResponseBarang(

    @field:SerializedName( "pesan")
    val pesan: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class DataItem(

    @field:SerializedName("nama_pelanggan")
    val nama_orang: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? =null,

    @field:SerializedName("jenis_kelamin")
    val jumlah_uang: String? =null,
)

