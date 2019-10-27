package com.iavariav.marketplacesmk10.ui.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.iavariav.marketplacesmk10.R
import com.iavariav.marketplacesmk10.rest.ApiConfig
import kotlinx.android.synthetic.main.activity_detail_produk.*
import kotlinx.android.synthetic.main.content_detail_produk.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailProdukActivity : AppCompatActivity() {

    private var idBarang: String? = null
    private var namaBarang: String? = null
    private var imageBarang: String? = null
    private var deskripsiBarang: String? = null
    private var hargaBarang: String? = null
    private var stokBarang: String? = null

    var myMediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        myMediaPlayer = MediaPlayer.create(this, R.raw.battle);


        idBarang = intent.getStringExtra("ID_BARANG")
        namaBarang = intent.getStringExtra("NAMA_BARANG")
        imageBarang = intent.getStringExtra("IMAGE_BARANG")
        deskripsiBarang = intent.getStringExtra("DESKRIPSI_BARANG")
        hargaBarang = intent.getStringExtra("HARGA_BARANG")
        stokBarang = intent.getStringExtra("STOK_BARANG")

        supportActionBar?.title = namaBarang
        Glide.with(this).load(imageBarang).override(512, 512)
            .into(ivBackdropProduk)

        tvHargaBarang.text = hargaBarang
        tvstokBarang.text = stokBarang
        tvDeskripsiBarang.text = deskripsiBarang

        fab.setOnClickListener {
            myMediaPlayer?.start()
            val apiService = ApiConfig.getApiService()
            apiService.beliData(idBarang!!).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@DetailProdukActivity, "Sukses",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finishAffinity()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        this@DetailProdukActivity, "" + t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("ID_BARANG", idBarang)
            intent.putExtra("NAMA_BARANG", namaBarang)
            intent.putExtra("IMAGE_BARANG", imageBarang)
            intent.putExtra("DESKRIPSI_BARANG", deskripsiBarang)
            intent.putExtra("HARGA_BARANG", hargaBarang)
            intent.putExtra("STOK_BARANG", stokBarang)
            startActivity(intent)
        }

    }
}
