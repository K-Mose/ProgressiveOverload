package com.example.progressiveoverload.data

data class User(
    var phone: String,
    var password: String,
    val createdAt: String,
    var updatedAt: String,
    var firstName: String?,
    var lastName: String?,
    var profileImage: String?,
    var gender: String?,
    var birth: String?
)
