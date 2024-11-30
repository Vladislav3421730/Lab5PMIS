package com.example.lab5pmis


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.lab5pmis.ui.theme.Lab5PMISTheme

import com.example.lab5pmis.utils.ListItem

import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.infoapp.ui_components.MainScreen

import com.example.lab5pmis.utils.ItemSaver
import com.example.lab5pmis.utils.Routes
import com.example.lab5pmis.ui_components.InfoScreen;


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var item = rememberSaveable(stateSaver = ItemSaver) {
                mutableStateOf(ListItem("", "", ""))
            }
            val navController = rememberNavController()
            Lab5PMISTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN.route)
                {
                    composable(Routes.MAIN_SCREEN.route) {
                        MainScreen(context = this@MainActivity) { listItem ->
                            item.value =
                                ListItem(listItem.title, listItem.imageName, listItem.htmlName)

                            navController.navigate(Routes.INFO_SCREEN.route)
                        }
                    }
                    composable(Routes.INFO_SCREEN.route) { InfoScreen(item = item.value!!)
                    }
                }
            }
        }
    }
}