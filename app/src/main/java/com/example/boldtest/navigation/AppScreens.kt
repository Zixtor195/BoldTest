package com.example.boldtest.navigation

import com.example.boldtest.utils.Constants.HOME_SCREEN
import com.example.boldtest.utils.Constants.SPLASH_SCREEN

sealed class AppScreens(val route: String) {

    data object HomeScreen : AppScreens(HOME_SCREEN)
    data object SplashScreen : AppScreens(SPLASH_SCREEN)


}



