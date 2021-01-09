package com.ashish.pixel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ashish.pixel.data.models.PixelImage

@Database(entities = [PixelImage::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun pixelImageDao(): PixelImageDao

    companion object {
        const val DB_NAME = "images_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME).build()
                //).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}