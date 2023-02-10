package ru.silantevdr.noteappmvvm.database.room.repository


import androidx.lifecycle.LiveData
import ru.silantevdr.noteappmvvm.database.DatabaseRepository
import ru.silantevdr.noteappmvvm.database.room.dao.NoteRoomDao
import ru.silantevdr.noteappmvvm.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao): DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note)
        onSuccess()
    }

    override fun signOut() {}
}