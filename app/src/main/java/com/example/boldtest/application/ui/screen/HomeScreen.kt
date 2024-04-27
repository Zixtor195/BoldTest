package com.example.boldtest.application.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.boldtest.application.ui.viewmodel.HomeScreenViewModel
import com.example.boldtest.application.ui.viewmodel.states.ForecastInformation
import com.example.boldtest.utils.widgets.AutocompleteDropDownMenu
import com.example.boldtest.utils.widgets.WeatherCardView
import com.example.boldtest.R
import com.example.boldtest.utils.Constants
import com.example.boldtest.utils.Constants.HOME_SCREEN
import com.example.boldtest.utils.Constants.WEATHER_CARD_VIEW_TAG

@Composable
fun HomeScreen(navController: NavHostController, homeScreenViewModel: HomeScreenViewModel) {

    val textToComplete: String by homeScreenViewModel.textToComplete.collectAsState()
    val locationList: List<String> by homeScreenViewModel.locationList.collectAsState()
    val forecastInformation: ForecastInformation by homeScreenViewModel.forecastInformation.collectAsState()


    Column(
        modifier = Modifier
            .testTag(HOME_SCREEN)
            .paint(
                painterResource(id = R.drawable.home_background),
                colorFilter = ColorFilter.tint(Color.White, blendMode = BlendMode.Color),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)

        ) {
            AutocompleteDropDownMenu(
                placeHolderText = "Digita tu locacion",
                textToComplete = textToComplete,
                isExpanded = homeScreenViewModel.isExpandedAutocompleteDropDownMenu(),
                itemList = locationList,
                onClickItem = {
                    homeScreenViewModel.onSelectLocationName(it)
                },
                onTextFieldChange = { homeScreenViewModel.onSearchTextFieldChange(it) }
            )

            Text(
                text = forecastInformation.country,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Text(
                text = forecastInformation.name,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

        }

        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(forecastInformation.forecastDayList.size) {
                forecastInformation.forecastDayList[it].let { forecastDay ->
                    WeatherCardView(
                        tag = "$WEATHER_CARD_VIEW_TAG $it",
                        date = forecastDay.date,
                        conditionText = forecastDay.conditionText,
                        conditionIcon = forecastDay.conditionIcon,
                        averageTemperature = forecastDay.averageTemperature
                    )
                }
            }
        }

    }
}