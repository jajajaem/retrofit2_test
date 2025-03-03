package com.test.retrofit_test

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.retrofit_test.recyclerView.CustomAdapter
import com.test.retrofit_test.retrofit2.ApiInterface
import com.test.retrofit_test.retrofit2.RetrofitInstance
import com.test.retrofit_test.retrofit2.userInfoItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var tv1: EditText
    private lateinit var tv2: TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button

    //val listItem = arrayListOf<listlayout>()
    private val userList = arrayListOf<userInfoItem>()
    val customAdapter = CustomAdapter(userList)
    var input = HashMap<String, Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        btn1 = findViewById(R.id.btn_1)
        btn2 = findViewById(R.id.btn_2)

        input["id"] = "11"
        input["phone"] = "123123123"
        input["emain"] = "woaks@kajc.ci"


        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        btn1.setOnClickListener {
            retrofit_get()
        }
        btn2.setOnClickListener {
            retrofit_post()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //레트로핏 get
    private fun retrofit_get() {
        val retrofitInstance = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        retrofitInstance.getUserData(Integer.parseInt(tv1.text.toString())).enqueue(object : Callback<List<userInfoItem>> {
            override fun onResponse(
                p0: Call<List<userInfoItem>>,
                p1: Response<List<userInfoItem>>
            ) {
                if (p1.isSuccessful) {
                    val users = p1.body() ?: emptyList()
                    userList.clear()
                    userList.addAll(users)
                    customAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(p0: Call<List<userInfoItem>>, p1: Throwable) {
                tv2.text = "실패했습니다."
            }
        })
    }

    private fun retrofit_post(){
        val retrofitInstance = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        retrofitInstance.getPostList(input).enqueue(object : Callback<userInfoItem>{
            override fun onResponse(p0: Call<userInfoItem>, p1: Response<userInfoItem>) {
                Log.d(TAG, "post ok")
                Log.d(TAG, "${p1.body()}")
                tv2.text="post 성공"

            }

            override fun onFailure(p0: Call<userInfoItem>, p1: Throwable) {
                tv2.text="post 실패"
            }
        })
    }

}