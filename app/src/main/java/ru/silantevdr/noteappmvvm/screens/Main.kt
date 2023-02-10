package ru.silantevdr.noteappmvvm.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.silantevdr.noteappmvvm.MainViewModel
import ru.silantevdr.noteappmvvm.MainViewModelFactory
import ru.silantevdr.noteappmvvm.model.Note
import ru.silantevdr.noteappmvvm.navigation.NavRoute
import ru.silantevdr.noteappmvvm.ui.theme.NoteAppMVVMTheme
import ru.silantevdr.noteappmvvm.utils.Constants.Keys.EMPTY
import ru.silantevdr.noteappmvvm.utils.DB_TYPE
import ru.silantevdr.noteappmvvm.utils.TYPE_FIREBASE
import ru.silantevdr.noteappmvvm.utils.TYPE_ROOM

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add ,
                    contentDescription = "Add Icons",
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn {
            items(notes) { note->
                NoteItem(note = note, navController = navController)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    val noteId = when(DB_TYPE) {
        TYPE_FIREBASE -> note.firebaseId
        TYPE_ROOM -> note.id
        else -> EMPTY
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${noteId}")
            },
        elevation = 6.dp
    ) {
        Column(
            Modifier
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = note.subtitle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    NoteAppMVVMTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        MainScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}