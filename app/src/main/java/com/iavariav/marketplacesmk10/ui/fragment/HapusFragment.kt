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
import com.iavariav.marketplacesmk10.adapter.HapusProdukAdapter
import com.iavariav.marketplacesmk10.model.ProdukModel
import com.iavariav.marketplacesmk10.rest.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class HapusFragment : Fragment() {


    private var rVItem: RecyclerView? = null
    private var hapusProdukAdapter: HapusProdukAdapter? = null
    private var produkModel: ArrayList<ProdukModel>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_hapus, container, false)
        rVItem = view.findViewById(R.id.rvItem)
        getData()
        return view
    }

    private fun getData() {
        produkModel = ArrayList()

        val apiService = ApiConfig.getApiService()
        apiService.ambilData().enqueue(object : Callback<ArrayList<ProdukModel>> {
            override fun onResponse(
                call: Call<ArrayList<ProdukModel>>,
                response: Response<ArrayList<ProdukModel>>
            ) {
                if (response.isSuccessful) {
                    produkModel?.clear()
                    produkModel = response.body()
                    hapusProdukAdapter = HapusProdukAdapter(activity, produkModel)
                    rVItem?.setLayoutManager(GridLayoutManager(activity, 2))
                    rVItem?.setAdapter(hapusProdukAdapter)
                    hapusProdukAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<ProdukModel>>, t: Throwable) {
                Toast.makeText(activity, "" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


}
