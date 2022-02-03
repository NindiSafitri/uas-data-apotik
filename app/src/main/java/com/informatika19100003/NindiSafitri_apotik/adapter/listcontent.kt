package com.informatika19100003.NindiSafitri_apotik.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.informatika19100003.NindiSafitri_apotik.R
import com.informatika19100003.NindiSafitri_apotik.model.DataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListContent(val ldata : List<DataItem?>?, val context: Context, val kondisi : String) :
    RecyclerView.Adapter<ListContent.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val nama_pelanggan = view.findViewById<TextView>(R.id.tv_nama_pelanggan)
        val jenis_kelamin = view.findViewById<TextView>(R.id.tv_jenis_kelamin)
        val alamat = view.findViewById<TextView>(R.id.alamat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return ldata!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = ldata?.get(position)
        holder.editBarang.setOnClickListener {
            val i = Intent(context, UpdateDataActivity::class.java)
            i.putExtra("nama_pelanggan", model?.nama_pelanggan)
            i.putExtra("jenis_kelamin", model?.jenis_kelamin)
            i.putExtra("tanggal", model?.tanggal)
            context.startActivity(i)
        }
        holder.deleteBarang.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete" + model?.nama_orang)
                .setMessage("Apakah Anda Ingin Mengahapus Data Ini?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    koneksi.service.deleteBarang(model?.id).enqueue(object : Callback<responactioninfak>{
                        override fun onFailure(call: Call<responactioninfak>, t: Throwable) {
                            Log.d("pesan1", t.localizedMessage)
                        }

                        override fun onResponse(
                            call: Call<responactioninfak>,
                            response: Response<responactioninfak>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show()
                                notifyDataSetChanged()
                                notifyItemRemoved(position)
                                notifyItemChanged(position)
                                notifyItemRangeChanged(position, ldata!!.size)

                                if(kondisi == " InsertDataActivity"){
                                    val activity = (context as InsertDataActivity)
                                    activity.getData()
                                }else if(kondisi == " UpdateDataActivity"){
                                    val activity = (context as UpdateDataActivity)
                                    activity.getData()
                                }else{
                                    val activity = (context as MainActivity)
                                    activity.getData()

                                }
                                Log.d("bpesan", response.body().toString())

                            }
                        }
                    })
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
        }
    }


}
