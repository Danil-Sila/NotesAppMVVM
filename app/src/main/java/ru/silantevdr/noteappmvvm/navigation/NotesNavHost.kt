package ru.silantevdr.noteappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.silantevdr.noteappmvvm.MainViewModel
import ru.silantevdr.noteappmvvm.screens.AddScreen
import ru.silantevdr.noteappmvvm.screens.MainScreen
import ru.silantevdr.noteappmvvm.screens.NoteScreen
import ru.silantevdr.noteappmvvm.screens.StartScreen
import ru.silantevdr.noteappmvvm.utils.Constants.Keys.ID
import ru.silantevdr.noteappmvvm.utils.Constants.Screens.ADD_SCREEN
import ru.silantevdr.noteappmvvm.utils.Constants.Screens.MAIN_SCREEN
import ru.silantevdr.noteappmvvm.utils.Constants.Screens.NOTE_SCREEN
import ru.silantevdr.noteappmvvm.utils.Constants.Screens.START_SCREEN

sealed class NavRoute(val route: String) {
    object Start: NavRoute(START_SCREEN)
    object Main: NavRoute(MAIN_SCREEN)
    object Add: NavRoute(ADD_SCREEN)
    object Note: NavRoute(NOTE_SCREEN)
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{$ID}") { backStackEntry ->
            NoteScreen(navController = navController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID))
        }
    }
}