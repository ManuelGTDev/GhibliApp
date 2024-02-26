package com.manugtdev.ghibliapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.presentation.ui.theme.Nunito

@Composable
fun FilmItem(film: Film, onItemClick: (Film) -> Unit) {
    val isDarkMode = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onItemClick(film)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(170.dp),
            model = film.movie_banner,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = film.title,
                fontFamily = Nunito,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkMode) Color.White else Color.Black
            )

            Row(modifier = Modifier.padding(end = 10.dp)) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Release Date",
                    tint = Color(0xFF03A9F4)
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    modifier = Modifier.padding(end = 5.dp),
                    text = film.release_date,
                    fontFamily = Nunito,
                    maxLines = 4,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    color = if (isDarkMode) Color.White else Color.Black
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier
                    .width(220.dp),
                text = film.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = Nunito,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkMode) Color.White else Color.Black
            )

            Row(modifier = Modifier.padding(end = 5.dp)) {

                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Default.StarBorder,
                    contentDescription = "Release Date",
                    tint = Color(0xFF03A9F4)
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    modifier = Modifier.padding(end = 29.dp),
                    text = film.rt_score,
                    fontFamily = Nunito,
                    maxLines = 4,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    color = if (isDarkMode) Color.White else Color.Black
                )
            }

        }


    }
    Row(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .border(1.dp, Color(0xFF6D6D6D))
            .fillMaxWidth()
            .height(1.dp)
    ) {

    }

}