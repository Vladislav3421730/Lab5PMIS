package com.example.lab5pmis.ui_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, drawerState: DrawerState, onFavClick: () -> Unit) {
    val coroutine = rememberCoroutineScope()
    TopAppBar (title =
        { Text(text = chooseTitle(title) ) }, navigationIcon ={
        IconButton(onClick = { coroutine.launch { drawerState.open() } }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu"
            )
        }
    }, actions = {
        IconButton(onClick = { onFavClick() }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite"
            )
        }
    } )
}

fun chooseTitle(title: String): String{
    if (title=="Marks") return "Марки автомобилей"
    else if (title=="Mersedes_Benz") return "Mersedes-Benz"
    else return title
}