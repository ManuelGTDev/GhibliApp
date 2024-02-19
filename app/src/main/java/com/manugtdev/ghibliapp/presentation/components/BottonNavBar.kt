package com.manugtdev.ghibliapp.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manugtdev.ghibliapp.R
import com.manugtdev.ghibliapp.presentation.navigation.Routes

@Composable
fun BottonNavigationBar(navController: NavController) {

    var isHomeSelected by remember { mutableStateOf(true) }
    var isLibrarySelected by remember { mutableStateOf(false) }

    BottomAppBar(modifier = Modifier.height(60.dp)) {
        NavigationBarItem(
            label = { },
            selected = isHomeSelected,
            onClick = {
                isHomeSelected = true
                isLibrarySelected = false
                navController.navigate(Routes.HomeScreen.route)
        }, icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF03A9F4),
                imageVector = ImageVector.vectorResource(R.drawable.ic_home),
                contentDescription = "Home",
            )
        })
        NavigationBarItem(
            label = { },
            selected = isLibrarySelected,
            onClick = {
                isHomeSelected = false
                isLibrarySelected = true
                navController.navigate(Routes.LibraryScreen.route)
        }, icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF03A9F4),
                imageVector = ImageVector.vectorResource(R.drawable.ic_bookmark),
                contentDescription = "Library"
            )
        })
    }

}