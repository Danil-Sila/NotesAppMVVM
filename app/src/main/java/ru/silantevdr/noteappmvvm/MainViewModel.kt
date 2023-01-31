package ru.silantevdr.noteappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.silantevdr.noteappmvvm.database.room.AppRoomDatabase
import ru.silantevdr.noteappmvvm.database.room.repository.RoomRepository
import ru.silantevdr.noteappmvvm.model.Note
import ru.silantevdr.noteappmvvm.utils.REPOSITORY
import ru.silantevdr.noteappmvvm.utils.TYPE_FIREBASE
import ru.silantevdr.noteappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: ()-> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}