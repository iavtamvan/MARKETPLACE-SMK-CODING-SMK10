package com.iavariav.marketplacesmk10.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iavariav.marketplacesmk10.R
import com.iavariav.marketplacesmk10.model.ProdukModel
import com.iavariav.marketplacesmk10.rest.ApiConfig
import com.iavariav.marketplacesmk10.ui.activity.DetailProdukActivity
import com.iavariav.marketplacesmk10.ui.activity.MainActivity
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.ArrayList

class HapusProdukAdapter(private val context: FragmentActivity?,
                         private val produkModel: ArrayList<ProdukModel>?
)
    : RecyclerView.Adapter<HapusProdukAdapter.ViewHolder>()

{
    private var id: String? = null
    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val iVProduk:ImageView = view.findViewById(R.id.IVProduk)
        val tvNama:TextView = view.findViewById(R.id.TVNama)
        val tvHargaProduk:TextView = view.findViewById(R.id.TVHarga)
        val btnDelete:Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HapusProdukAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_delete, parent,
                false))
    }

    override fun getItemCount(): Int {
        return produkModel!!.size
    }

    override fun onBindViewHolder(holder: HapusProdukAdapter.ViewHolder, position: Int) {
        holder.tvNama.text = produkModel!!.get(position).namaBarang
        holder.tvHargaProduk.text = produkModel!!.get(position).hargaBarang
        context?.let {
            Glide.with(it)
                .load(produkModel.get(position).imageBarang)
                .override(512, 512)
                .into(holder.iVProduk)
        }

        holder.btnDelete.setOnClickListener {
            id = produkModel.get(position).idBarang
            val apiService = ApiConfig.getApiService()
            id?.let { it1 ->
                apiService.deleteData(it1).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                val jsonObject = JSONObject(response.body()!!.string())
                                val error = jsonObject.optString("error_msg")
                                Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show()
                                context?.startActivity(Intent(context, MainActivity::class.java))
                                (context as Activity).finishAffinity()
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

    }

}