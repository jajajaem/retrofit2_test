package com.test.retrofit_test.retrofit2

data class list1(
    val data: List<userInfoI>
)

data class userInfoI(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)