package com.iavariav.marketplacesmk10.rest

import com.iavariav.marketplacesmk10.model.ProdukModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.ArrayList

interface ApiService {

    @GET("api_get.php")
    //mengambil data dari api_get.php
    fun ambilData(): Call<ArrayList<ProdukModel>>
    //metode ambilData memanggil ArrayList dari ProdukModel

    @FormUrlEncoded
    @POST("api_login.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api_tambah_barang.php")
    fun tambahData(
        @Field("nama_barang") nama_barang: String,
        @Field("image_barang") image_barang: String,
        @Field("deskripsi_barang") deskripsi_barang: String,
        @Field("harga_barang") harga_barang: String,
        @Field("stok_barang") stok_barang: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api_hapus_barang.php")
    fun deleteData(@Field("id_barang") id: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api_beli_barang.php")
     fun beliData(
        @Field("id_barang") id_barang: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api_update_barang.php")
    fun updateData(
        @Field("id_barang") id_barang: String,
        @Field("nama_barang") nama_barang: String,
        @Field("image_barang") image_barang: String,
        @Field("deskripsi_barang") deskripsi_barang: String,
        @Field("harga_barang") harga_barang: String,
        @Field("stok_barang") stok_barang: String
    ): Call<ResponseBody>


}