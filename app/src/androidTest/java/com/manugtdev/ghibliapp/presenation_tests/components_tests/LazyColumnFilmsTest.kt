package com.manugtdev.ghibliapp.presenation_tests.components_tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.presentation.components.LazyColumnFilms
import org.junit.Rule
import org.junit.Test

class LazyColumnFilmsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLazyColumnFilmsIsCreatedCheckIfItIsOk() {
        composeTestRule.setContent {
            val filmList: List<Film> = emptyList()
            val isLoading = true
            LazyColumnFilms(filmList, isLoading) {}
        }
        composeTestRule.onNodeWithTag("lazyColumnFilms").assertExists()
    }

    @Test
    fun whenLazyColumnFilmsIslickedCheckOk() {
        composeTestRule.setContent {
            val filmList: List<Film> = emptyList()
            val isLoading = true
            LazyColumnFilms(filmList, isLoading) {}
        }
        composeTestRule.onNodeWithTag("lazyColumnFilms").performClick()
        composeTestRule.onNodeWithTag("lazyColumnFilms").assertExists()
    }

}