package com.manugtdev.ghibliapp.presenation_tests.components_tests

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.manugtdev.ghibliapp.domain.model.Film
import com.manugtdev.ghibliapp.presentation.components.FilmItem
import org.junit.Rule
import org.junit.Test

class FilmItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenFilmItemIsCreadetCheckIfItIsOk() {
        composeTestRule.setContent {
            val film = Film()
            FilmItem(film){}
        }
        composeTestRule.onNodeWithTag("filmItem").assertExists()
    }

    @Test
    fun whenFilmItemIsClickedCheckOk() {
        composeTestRule.setContent {
            val film = Film()
            FilmItem(film){}
        }
        composeTestRule.onNodeWithTag("filmItem").performClick()
        composeTestRule.onNodeWithTag("filmItem").assertExists()
    }

}