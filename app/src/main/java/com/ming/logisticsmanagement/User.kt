package com.ming.logisticsmanagement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(userName:String, password: String) {
    @PrimaryKey
    var userName:String = userName

    @ColumnInfo(name = "password")
    var password:String = password

}