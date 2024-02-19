package com.manugtdev.ghibliapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.manugtdev.ghibliapp.R

@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SearchBarMain(searchText: String, onValueChange: (String) -> Unit) {


    TextField(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .border(1.dp, Color(0xFF242424),
                shape = RoundedCornerShape(15.dp)),
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
            containerColor = Color(0xFF1F1F1F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )


}