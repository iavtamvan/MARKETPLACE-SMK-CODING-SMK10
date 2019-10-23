package com.iavariav.marketplacesmk10.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.iavariav.marketplacesmk10.R
import com.iavariav.marketplacesmk10.ui.fragment.HapusFragment
import com.iavariav.marketplacesmk10.ui.fragment.TambahBarangFragment
import com.iavariav.marketplacesmk10.ui.fragment.TampilFragment
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = FragmentPagerItemAdapter(
            supportFragmentManager, FragmentPagerItems.with(this)
                .add("Produk", TampilFragment::class.java)
                .add("Tambah Produk", TambahBarangFragment::class.java)
                .add("Hapus Produk", HapusFragment::class.java)
                .create()
        )
        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.adapter = adapter

        val viewPagerTab = findViewById<View>(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)

    }


}
