package com.github.kuya32.vintracker.feature_auth.data.local

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: UserEntity)

    @Update
    suspend fun updateUserInfo(user: UserEntity)

    @Query("DELETE FROM userentity WHERE id = :id")
    suspend fun deleteUserInfo(id: Int)
}