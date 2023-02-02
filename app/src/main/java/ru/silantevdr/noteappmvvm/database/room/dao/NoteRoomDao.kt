package ru.silantevdr.noteappmvvm.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.silantevdr.noteappmvvm.model.Note
import ru.silantevdr.noteappmvvm.utils.Constants.Keys.NOTES_TABLE

@Dao
interface NoteRoomDao {
    @Query("SELECT * FROM $NOTES_TABLE")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun  updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note:Note)
}