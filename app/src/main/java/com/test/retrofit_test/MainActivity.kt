package com.test.retrofit_test

import android.os.Bundle
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

class MainActivity : AppCompatActivity() {
    private lateinit var tv1: EditText
    private lateinit var tv2: TextView
    private lateinit var btn1: Button

    //val listItem = arrayListOf<listlayout>()
    private val userList = arrayListOf<userInfoItem>()
    val customAdapter = CustomAdapter(userList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        btn1 = findViewById(R.id.btn_1)


        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

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

    //레트로핏 get
    private fun aa() {
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

}