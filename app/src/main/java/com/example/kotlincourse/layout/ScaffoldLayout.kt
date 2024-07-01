package com.example.kotlincourse.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldLayout(modifier: Modifier = Modifier) {
    
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "App Bar") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        
                        Icon(imageVector = Icons.Default.Menu, contentDescription ="Menu Icon" )
                        
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                        
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Search Icon")

                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "Search Icon")

                    }
                }
                )
        },
        bottomBar = {
            BottomAppBar{
               Row(modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween) {
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Default.Home, contentDescription = "Home Icon")
                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shoping Cart Icon")
                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite Icon")
                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Favorite Ic")
                   }
               }
            }
        }
    )
    {
        padding ->
        Column(modifier = Modifier.padding(padding)) {
           ImageSliderPreview()

        }
    }
}

