package com.example.finhealth.data.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finhealth.data.models.GainOutlayDao
import com.example.finhealth.data.models.GainOutlayModel

@Database(entities = [GainOutlayModel::class], version = 1)
abstract class DataBaseROOM : RoomDatabase(){
    abstract fun getGainOutlayDao(): GainOutlayDao
}

fun openDB(context: Context): DataBaseROOM {
    return Room.databaseBuilder(
        context.applicationContext,
        DataBaseROOM::class.java,
        name = "FinHealthRoom.db"
    ).build()
}