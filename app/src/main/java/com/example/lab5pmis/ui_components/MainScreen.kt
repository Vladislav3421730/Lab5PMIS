package com.example.infoapp.ui_components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lab5pmis.MainViewModel
import com.example.lab5pmis.ui_components.DrawerMenu
import com.example.lab5pmis.ui_components.MainListItem
import com.example.lab5pmis.ui_components.MainTopBar
import com.example.lab5pmis.utils.DrawerEvents
import com.example.lab5pmis.utils.ListItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onClick: (ListItem) -> Unit
) {
    var topBarTitle = rememberSaveable { mutableStateOf("Марки автомобилей") }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState()
    val mainlist = mainViewModel.mainList

    LaunchedEffect(Unit) { mainViewModel.getAllItemsByCategory("Marks") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerMenu() { event ->
                    when (event) {
                        is DrawerEvents.OnItemClick -> {
//                            ind.value = event.index
                            topBarTitle.value = event.title
//                            mainList.value = getListItemsByIndex(
//                                ind.value, context
//                            )
                            mainViewModel.getAllItemsByCategory(event.title)
                            scope.launch {
                                listState.scrollToItem(0)
                            }
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        },
        content = {
            Scaffold(topBar = {
                MainTopBar(
                    title = topBarTitle.value,
                    drawerState = drawerState
                ) {
                    topBarTitle.value = "Избранные"
                    mainViewModel.getFavorites () }
            }
            ) { innerPadding ->
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    items(mainlist.value) { item ->
                        MainListItem(item = item) { listItem ->
                            onClick(listItem)
                        }
                    }
                }
            }
        }
    )
}