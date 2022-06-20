package com.github.kuya32.vintracker.feature_auth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: UserEntity)

    @Query("DELETE FROM userentity WHERE id = :id")
    suspend fun deleteUserInfo(id: Int)
}