package com.informatika19100003.NindiSafitri_apotik

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.informatika19100003.NindiSafitri_apotik.model.responactioninfak
import com.informatika19100003.NindiSafitri_apotik.network.koneksi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val etNamapelanggan = et_Namapelanggan.text
            val etjeniskelamin = et_jeniskelamin.text
            val ettanggal = et_tanggal .text
            if (etnamapelanggan.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "nama Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (etjeniskelaminisEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "jenis kelamin Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val etnama_pelanggan = null
                val etjenis_kelamin = null
                val ettanggal = null
                actionData(etnama_pelanggan.toString(), ettanggal.toString(), etjenis_kelamin.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }


    }

    fun actionData(nama_pelanggan: String, tanggal: String, jenis_kelamin: String) {
        koneksi.service.insertBarang(nama_pelanggan, tanggal,jenis_kelamin)
            .enqueue(object : Callback<responactioninfak> {
                override fun onFailure(call: Call<responactioninfak>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<responactioninfak>,
                    response: Response<responactioninfak>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getBarang().enqueue(object : Callback<responinfak> {
            override fun onFailure(call: Call<responinfak>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<responinfak>,
                response: Response<responinfak>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity, "InsertDataActivity")

                    rv_data_barang.apply {
                        var adapter = rvAdapter
                        var layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }

    class LinearLayoutManager(insertDataActivity: InsertDataActivity) {

    }

    class ListContent(datacontent: Any, insertDataActivity: InsertDataActivity, s: String) {

    }

    class responinfak {

        val data: Any
            get() {
                TODO()
            }
    }
}

fun <T> Call<T>.enqueue(callback: Callback<InsertDataActivity.responinfak>) {
    TODO("Not yet implemented")
}

