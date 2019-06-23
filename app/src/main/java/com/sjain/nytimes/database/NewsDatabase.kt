package com.sjain.nytimes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sjain.nytimes.model.NewsItem

@Database(entities = arrayOf(NewsItem::class),version = 1,exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsItemDao(): NewsItemDao

    companion object {
        lateinit var INSTANCE: NewsDatabase

        fun getAppDataBase(context: Context): NewsDatabase? {
            if (INSTANCE == null) {
                synchronized(NewsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "newsdb")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

    }

}