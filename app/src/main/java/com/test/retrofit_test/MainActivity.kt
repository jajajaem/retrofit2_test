package com.test.retrofit_test

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.test.retrofit_test.retrofit2.userInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        val retrofitInstance = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        retrofitInstance.getUserData(tv1.text.toString()).enqueue(object : Callback<userInfo>{
            override fun onResponse(p0: Call<userInfo>, p1: Response<userInfo>) {
                val result = p1.body()
                tv2.text =result.toString()
            }

            override fun onFailure(p0: Call<userInfo>, p1: Throwable) {
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}