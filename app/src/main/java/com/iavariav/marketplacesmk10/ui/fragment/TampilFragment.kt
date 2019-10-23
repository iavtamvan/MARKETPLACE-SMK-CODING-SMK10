package com.iavariav.marketplacesmk10.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.iavariav.marketplacesmk10.R
import com.iavariav.marketplacesmk10.adapter.TampilProdukAdapter
import com.iavariav.marketplacesmk10.model.ProdukModel
import com.iavariav.marketplacesmk10.rest.ApiConfig
import kotlinx.android.synthetic.main.fragment_tampil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class TampilFragment : Fragment() {
    var produkModels: ArrayList<ProdukModel>? = null
    var tampilProdukAdapter: TampilProdukAdapter? = null

    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_tampil, container, false)
        recyclerView = view.findViewById(R.id.rvProduk)
        produkModels = ArrayList()
        getData()
        return view
    }

    private fun getData() {
        val apiService = ApiConfig.getApiService()
        apiService.ambilData().enqueue(object : Callback<ArrayList<ProdukModel>> {
            override fun onResponse(
                call: Call<ArrayList<ProdukModel>>,
                response: Response<ArrayList<ProdukModel>>
            ) {
                if (response.isSuccessful) {
                    produkModels = response.body()
                    tampilProdukAdapter = TampilProdukAdapter(activity, produkModels)
                    rvProduk.setAdapter(tampilProdukAdapter)
                    rvProduk.setLayoutManager(GridLayoutManager(activity, 2))
                    tampilProdukAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<ProdukModel>>, t: Throwable) {
                Toast.makeText(
                    activity, "" + t.message,
                    Toast.LENGTH_SHORT
                ).show()

            }
        })

    }


}
