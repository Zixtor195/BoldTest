package com.example.boldtest

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.example.boldtest.navigation.AppNavigation
import com.example.boldtest.utils.Constants.AUTO_COMPLETE_CARD_TAG
import com.example.boldtest.utils.Constants.AUTO_COMPLETE_DROPDOWN_MENU_TAG
import com.example.boldtest.utils.Constants.HOME_SCREEN
import com.example.boldtest.utils.Constants.SPLASH_SCREEN_IMAGE_TAG
import com.example.boldtest.utils.Constants.WEATHER_CARD_VIEW_TAG
import com.example.boldtest.utils.application.AsyncTimer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EndToEndTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent {
            AppNavigation(rememberNavController())
        }
    }

    @Test
    fun testNavigationSuccess() {
        val resultText = "result"

        composeRule.onNodeWithContentDescription(SPLASH_SCREEN_IMAGE_TAG).assertIsDisplayed()
        AsyncTimer.start(3000)
        composeRule.waitUntil(condition = { AsyncTimer.expired }, timeoutMillis = 4000)
        composeRule.onNodeWithTag(HOME_SCREEN).assertIsDisplayed()
        composeRule.onNodeWithTag(AUTO_COMPLETE_DROPDOWN_MENU_TAG).performClick()
        composeRule.onNodeWithTag(AUTO_COMPLETE_DROPDOWN_MENU_TAG).assertIsFocused()
        composeRule.onNodeWithTag(AUTO_COMPLETE_DROPDOWN_MENU_TAG).performTextInput(resultText)
        composeRule.onNodeWithTag(AUTO_COMPLETE_DROPDOWN_MENU_TAG).assert(hasText(resultText))

        AsyncTimer.start(3000)
        composeRule.waitUntil(condition = { AsyncTimer.expired }, timeoutMillis = 4000)
        composeRule.onNodeWithTag(AUTO_COMPLETE_CARD_TAG).assertExists()
        composeRule.onNodeWithTag(AUTO_COMPLETE_CARD_TAG).performClick()

        AsyncTimer.start(3000)
        composeRule.waitUntil(condition = { AsyncTimer.expired }, timeoutMillis = 4000)

        composeRule.onNodeWithTag("$WEATHER_CARD_VIEW_TAG 0").assertExists()
    }

}

