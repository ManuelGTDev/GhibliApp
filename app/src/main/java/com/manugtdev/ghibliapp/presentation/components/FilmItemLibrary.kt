package com.manugtdev.ghibliapp.presentation.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.presentation.ui.theme.Nunito
import com.manugtdev.ghibliapp.presentation.viewmodels.LibraryViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmItemLibrary(film: Film, viewModel: LibraryViewModel, onItemClick: (Film) -> Unit) {
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .combinedClickable(onClick = {
                onItemClick(film)
            },
                onLongClick = {
                    showDialog.value = true
                })
    ) {
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Delete Film") },
                text = { Text("Are you sure you want to delete ${film.title}?") },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF03A9F4),
                            contentColor = Color.White
                        ),
                        onClick = {
                            viewModel.deleteFilm(film)
                            showDialog.value = false
                            Toast
                                .makeText(
                                    context,
                                    "Film deleted correctly",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    Button(colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF03A9F4),
                        contentColor = Color.White
                    ), onClick = { showDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        AsyncImage(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxHeight(),
            model = film.image, contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = film.title,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(top = 5.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = film.description,
                fontFamily = Nunito,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                Icon(
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF03A9F4),
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Release Date"
                )

                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    text = film.release_date,
                    fontFamily = Nunito,
                    maxLines = 4,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}