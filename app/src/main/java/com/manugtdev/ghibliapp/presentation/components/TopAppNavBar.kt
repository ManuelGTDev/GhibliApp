package com.manugtdev.ghibliapp.presentation.components

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manugtdev.ghibliapp.R
import com.manugtdev.ghibliapp.presentation.navigation.Routes

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun TopAppNavBar(
    navController: NavController,
    isFavorite: Boolean,
    filmName: String,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(30.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.navigate(Routes.MainScreen.route)
                },
            imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow),
            tint = Color(0xFF03A9F4),
            contentDescription = "Back"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onClick()
                        Toast
                            .makeText(context, "Film added to your libray", Toast.LENGTH_SHORT)
                            .show()
                    },
                imageVector = if (isFavorite) Icons.Default.Bookmark
                else Icons.Default.BookmarkBorder,
                tint = Color(0xFF03A9F4),
                contentDescription = "bookmark"
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        val url = "https://www.google.com/search?q=$filmName"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        launcher.launch(intent)
                    },
                imageVector = ImageVector.vectorResource(R.drawable.ic_network),
                tint = Color(0xFF03A9F4),
                contentDescription = "Back"
            )
        }

    }


}