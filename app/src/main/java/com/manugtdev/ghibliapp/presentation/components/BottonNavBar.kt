package com.manugtdev.ghibliapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manugtdev.ghibliapp.R
import com.manugtdev.ghibliapp.presentation.navigation.Routes

@Composable
fun BottonNavigationBar(navController: NavController) {

    var selectedIndex by remember { mutableIntStateOf(0) }

    BottomAppBar(
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            NavigationBarItem(
                isSelected = selectedIndex == 0,
                onClick = {
                    selectedIndex = 0
                    navController.navigate(Routes.HomeScreen.route)
                },
                icon = Icons.Default.Home,
                text = stringResource(id = R.string.home)
            )
            NavigationBarItem(
                isSelected = selectedIndex == 1,
                onClick = {
                    selectedIndex = 1
                    navController.navigate(Routes.LibraryScreen.route)
                },
                icon = Icons.Default.Bookmarks,
                text = stringResource(id = R.string.library)
            )
        }
    }
}

@Composable
fun NavigationBarItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    text: String
) {
    val isDarkMode = isSystemInDarkTheme()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected)
                colorResource(id = R.color.blue_icons) else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            color = if (isSelected) colorResource(id = R.color.blue_icons) else Color.Gray
        )
    }
}