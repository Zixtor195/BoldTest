package com.example.boldtest.utils.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.boldtest.utils.Constants.FORECAST_CONDITION_IMAGE_TEXT
import com.example.boldtest.ui.theme.smoothWhite


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherCardView(
    tag: String,
    date: String,
    averageTemperature: String,
    conditionText: String,
    conditionIcon: String,
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardColors(
            containerColor = smoothWhite,
            contentColor = smoothWhite,
            disabledContainerColor = smoothWhite,
            disabledContentColor = smoothWhite,
        ),
        modifier = Modifier
            .testTag(tag)
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = smoothWhite)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = date,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.Black
                )

                Text(
                    text = "$averageTemperature C",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

            }
            Column(modifier = Modifier.weight(1f)) {
                GlideImage(
                    model = conditionIcon,
                    contentDescription = FORECAST_CONDITION_IMAGE_TEXT,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(80.dp)
                        .align(Alignment.CenterHorizontally),
                )

                Text(
                    text = conditionText,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
    }

}