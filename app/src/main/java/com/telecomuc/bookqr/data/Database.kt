package com.telecomuc.bookqr.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [BookData::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun bookDao(): BookDao

}