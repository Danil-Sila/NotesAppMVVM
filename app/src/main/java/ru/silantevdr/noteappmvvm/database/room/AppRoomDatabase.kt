package ru.silantevdr.noteappmvvm.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.silantevdr.noteappmvvm.database.room.dao.NoteRoomDao
import ru.silantevdr.noteappmvvm.model.Note
import ru.silantevdr.noteappmvvm.utils.Constants.Keys.NOTE_DATABASE

@Database(entities = [Note::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun getRoomDao(): NoteRoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null
        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    NOTE_DATABASE
                ).build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }
}