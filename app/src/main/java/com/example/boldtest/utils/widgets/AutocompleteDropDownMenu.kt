package com.example.boldtest.utils.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boldtest.utils.Constants.AUTO_COMPLETE_CARD_TAG
import com.example.boldtest.utils.Constants.AUTO_COMPLETE_DROPDOWN_MENU_TAG


@Composable
fun AutocompleteDropDownMenu(
    placeHolderText: String,
    textToComplete: String,
    isExpanded: Boolean,
    itemList: List<String>,
    onClickItem: (String) -> Unit,
    onTextFieldChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(AUTO_COMPLETE_DROPDOWN_MENU_TAG)

                        .border(
                            width = 1.8.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(5.dp)
                        ),

                    value = textToComplete,
                    onValueChange = { onTextFieldChange(it) },
                    placeholder = {
                        Text(
                            text = placeHolderText,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .testTag(AUTO_COMPLETE_CARD_TAG),
                    shape = RoundedCornerShape(10.dp)
                ) {

                    LazyColumn(modifier = Modifier.heightIn(max = 150.dp)) {
                        items(itemList.size) {
                            ItemsCategory(title = itemList[it]) { item ->
                                onClickItem(item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemsCategory(
    title: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}


