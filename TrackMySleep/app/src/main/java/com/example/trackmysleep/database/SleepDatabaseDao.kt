package com.example.trackmysleep.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SleepDatabaseDao{

    @Insert
    fun insert(night: SleepNight)

    @Update
    fun update(night: SleepNight)

    @Delete
    fun delete(night: SleepNight)

    @Query("SELECT * FROM daily_sleep_quality WHERE nightId =:key")
    fun get(key: Long): SleepNight?

    @Query("DELETE FROM daily_sleep_quality")
    fun clear()

    @Query("SELECT * FROM daily_sleep_quality ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight?

    @Query("SELECT * FROM daily_sleep_quality ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>

}