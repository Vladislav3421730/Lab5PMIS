package com.example.lab5pmis

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.lab5pmis.ui.theme.Lab5PMISTheme
import com.example.lab5pmis.ui_components.DrawerMenu
import com.example.lab5pmis.ui_components.MainListItem
import com.example.lab5pmis.ui_components.MainTopBar
import com.example.lab5pmis.utils.DrawerEvents
import com.example.lab5pmis.utils.IdArrayList
import com.example.lab5pmis.utils.ListItem
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5PMISTheme {
                val topBarTitle = remember { mutableStateOf("Марки автомобилей") }
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val mainList = remember { mutableStateOf(getListItemsByIndex(0, this@MainActivity)) }

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            DrawerMenu { event ->
                                when (event) {
                                    is DrawerEvents.OnItemClick -> {
                                        topBarTitle.value = if (event.title == "Marks") "Марки автомобилей" else event.title
                                        mainList.value = getListItemsByIndex(event.index, this@MainActivity)
                                    }
                                }
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            MainTopBar(
                                title = topBarTitle.value,
                                drawerState = drawerState)
                        }
                    ) { innerPadding ->
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            items(mainList.value) { item -> MainListItem(item = item) }
                        }
                    }
                }
            }
        }
    }
}



private fun getListItemsByIndex(index: Int, context: Context): List<ListItem> {
    val list = ArrayList<ListItem>()
    val arrayList = context.resources.getStringArray(IdArrayList.listId[index])
    arrayList.forEach { item -> val itemArray = item.split("|")
        list.add( ListItem( itemArray[0],
            itemArray[1] ) )
    }
    return list
}