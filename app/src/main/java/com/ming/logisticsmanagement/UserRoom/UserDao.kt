package com.ming.logisticsmanagement.UserRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ming.logisticsmanagement.UserRoom.User

@Dao
interface UserDao {
    @Insert
    fun insertUsers(vararg users: User?)

    @Query("SELECT * FROM User WHERE userName In(:userName)")//查询账号是否存在
    fun getUser(userName:String): LiveData<User>
}