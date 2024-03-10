package com.manugtdev.ghibliapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.manugtdev.ghibliapp.R

@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SearchBarMain(searchText: String, onValueChange: (String) -> Unit) {
    val isDarkMode = isSystemInDarkTheme()

    TextField(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .border(
                1.dp, Color(0xFFDDDDDD),
                shape = RoundedCornerShape(15.dp)
            ),
        textStyle = TextStyle(color = if (isDarkMode)Color.White else Color.Black),
        maxLines = 1,
        value = searchText,
        placeholder = { Text(text = "Search...") },
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = "Home"
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = if (isDarkMode)
                colorResource(id = R.color.almost_back) else Color(0xFFDFDFDF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )


}