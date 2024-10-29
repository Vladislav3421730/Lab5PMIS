package com.example.lab5pmis.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab5pmis.R
import com.example.lab5pmis.utils.DrawerEvents

@Composable
fun DrawerMenu(onEvent: (DrawerEvents) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image( painter = painterResource(id = R.drawable.backimage),
            contentDescription = "Main Bg Image",
            modifier = Modifier.fillMaxSize().alpha(0.7f), contentScale = ContentScale.Crop )
        Column(modifier = Modifier.fillMaxSize()) {
            Body()  {event->onEvent(event)}
        }
    }
}


@Composable
fun Body(onEvent: (DrawerEvents) -> Unit) {
    val list = stringArrayResource(id = R.array.drawer_list)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
        itemsIndexed(list) { index, title ->
            Card(
                modifier = Modifier.fillMaxSize()
                    .padding(3.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Text(
                    text = if (title == "Marks") "Марка автомобилей" else title,
                    modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onEvent(DrawerEvents.OnItemClick(title,index))
                    }
                    .padding(10.dp).wrapContentWidth(),
                    fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

