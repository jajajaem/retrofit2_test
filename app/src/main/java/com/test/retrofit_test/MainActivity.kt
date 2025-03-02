package com.test.retrofit_test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
    private lateinit var tv1: EditText
    private lateinit var tv2: TextView
    private lateinit var btn1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        btn1 = findViewById(R.id.btn_1)

        aa()

        btn1.setOnClickListener {
            aa()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun aa(){
        val retrofitInstance = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        retrofitInstance.getUserData(tv1.text.toString()).enqueue(object : Callback<userInfo> {
            override fun onResponse(p0: Call<userInfo>, p1: Response<userInfo>) {
                val result = p1.body()
                tv2.text = result.toString()
            }

            override fun onFailure(p0: Call<userInfo>, p1: Throwable) {
                tv2.text = "실패했습니다."
            }
        })
    }
}