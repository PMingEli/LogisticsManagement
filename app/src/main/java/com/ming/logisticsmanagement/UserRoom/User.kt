package com.ming.logisticsmanagement.UserRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(userName:String, password: String, city:String) {
    @PrimaryKey
    var userName:String = userName

    @ColumnInfo(name = "password")
    var password:String = password

    @ColumnInfo(name = "city")
    var city:String = city

    fun getcity(): String {
        return city
    }

}