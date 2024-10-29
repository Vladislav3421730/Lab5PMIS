package com.example.lab5pmis.utils

sealed class DrawerEvents {
    data class OnItemClick(
        val title: String,val index:Int):
        DrawerEvents()
}