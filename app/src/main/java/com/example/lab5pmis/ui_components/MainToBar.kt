package com.example.lab5pmis.ui_components

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.lab5pmis.InfoActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = {
                context.startActivity(Intent(context, InfoActivity::class.java))
            }) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "Доп. информация",
                    tint = Color.LightGray
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.LightGray,
            navigationIconContentColor = Color.LightGray,
            actionIconContentColor = Color.LightGray
        )
    )
}
