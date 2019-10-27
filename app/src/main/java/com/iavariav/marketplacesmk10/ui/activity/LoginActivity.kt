package com.iavariav.marketplacesmk10.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.iavariav.marketplacesmk10.R
import com.iavariav.marketplacesmk10.rest.ApiConfig
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        btn_login.setOnClickListener {
            postLogin()
            
        }
    }

    private fun postLogin() {
        val apiService = ApiConfig.getApiService()
        apiService.login(edt_username.text.toString().trim(), edt_password.text.toString().trim())
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        try {
                            val jsonObject = JSONObject(response.body()!!.string())
                            val error = jsonObject.optString("error_msg")
                            if (error.equals("Login Sukses", ignoreCase = true)) {
                                Toast.makeText(this@LoginActivity, "" + error, Toast.LENGTH_SHORT)
                                    .show()
                                finishAffinity()
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        MainActivity::class.java
                                    )
                                )

                                val sharedPreferences = getSharedPreferences("LSP", MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("SHARED_LOGIN", error)
                                editor.apply()
                            } else {
                                Toast.makeText(this@LoginActivity, "" + error, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    } else {
                        Toast.makeText(
                            this@LoginActivity, "Login Gagal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity, "" + t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
