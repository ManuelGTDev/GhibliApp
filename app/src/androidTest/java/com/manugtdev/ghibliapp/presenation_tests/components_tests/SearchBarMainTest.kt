package com.manugtdev.ghibliapp.presenation_tests.components_tests

import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import com.manugtdev.ghibliapp.presentation.components.SearchBarMain
import org.junit.Rule
import org.junit.Test

class SearchBarMainTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenSearchBarIsCreatedThenCheckIfItIsOk() {
        composeTestRule.setContent {
            SearchBarMain(searchText = "") {}
        }
        composeTestRule.onNodeWithTag("searchBar").assertExists()
    }

    @Test
    fun whenSearchBarKeyboardIsEnterClicked() {
        composeTestRule.setContent {
            SearchBarMain(searchText = "") {}
        }
        composeTestRule.onNodeWithTag("searchBar").performImeAction()
        composeTestRule.onNodeWithTag("searchBar").assertIsFocused()

    }
}