package com.iavariav.marketplacesmk10.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.iavariav.marketplacesmk10.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val sp = getSharedPreferences("SHARED_LOGIN", Context.MODE_PRIVATE)

            // TODO jika belum masuk ke LoginActivity
            if (sp!!.equals("") || TextUtils.isEmpty(sp.toString())) {
                finishAffinity()
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))

            }// TODO jika sudah nantinya akan masuk ke Home
        }, 2000) // TODO 2000 = 2 detik, 5000 = 5 detik dst.


    }
}
