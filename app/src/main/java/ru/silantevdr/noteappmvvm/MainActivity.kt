package ru.silantevdr.noteappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.silantevdr.noteappmvvm.navigation.NotesNavHost
import ru.silantevdr.noteappmvvm.ui.theme.NoteAppMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppMVVMTheme {
                // A surface container using the 'background' color from the theme
               Scaffold (
                   topBar = {
                       TopAppBar (
                            title = {
                                Text(text = "Notes App")
                            },
                           backgroundColor = Color.Blue,
                           contentColor = Color.White,
                           elevation = 12.dp
                       )
                   },
                   content = {
                        Surface (
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            NotesNavHost()
                        }
                   }
               )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppMVVMTheme {

    }
}