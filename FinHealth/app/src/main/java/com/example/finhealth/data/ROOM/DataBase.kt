package com.example.finhealth.data.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel


@Database(entities = [GainOutlayModel::class], version = 2)
abstract class DataBaseROOM : RoomDatabase() {
    abstract fun getGainOutlayDao(): GainOutlayDao

    companion object {
        @Volatile private var INSTANCE: DataBaseROOM? = null

        fun getInstance(context: Context): DataBaseROOM {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseROOM::class.java,
                    "FinHealthRoom.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE IF EXISTS table_Salary")  // Excluindo a tabela Salary
    }
}