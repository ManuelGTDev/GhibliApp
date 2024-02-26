package com.manugtdev.ghibliapp.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import com.manugtdev.ghibliapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Studio Ghibli App",
        description = "The best way to see all the important information about Studio Ghibli.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "A powerful search engine",
        description = "No matter what film it is, you can search for it with our powerful search engine.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Your own library",
        description = "Get your own library where you can save all the movies you have seen, " +
                "access more details or delete them from the library.",
        image = R.drawable.onboarding3
    )
)
