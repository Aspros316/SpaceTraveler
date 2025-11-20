package com.example.spacetraveler.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContractDataInput(
    textValue: String,
    placeHolder: String,
    value: String,
    onTextChanged: (String) -> Unit,
    leadingIcon: Painter,
    keyboardOptions: KeyboardOptions,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    val animatedFontSize by animateFloatAsState(
        targetValue = if (value.isEmpty()) 18f else 16f
    )

    Text(
        modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 0.dp),
        text = textValue,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 15.sp
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = { input ->
                onTextChanged(input)
            },
            keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF140D4F),
                unfocusedTextColor = Color(0xFF140D4F),

                cursorColor = Color(0xFF140D4F),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(size = 10.dp),
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF507DBC),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .height(50.dp),
            leadingIcon = {
                Icon(
                    painter = leadingIcon,
                    contentDescription = null,
                    tint = Color(0xFF140D4F),
                    modifier = Modifier.size(18.dp),
                )
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    color = Color.Blue,
                    textAlign = TextAlign.End,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.End,
                fontSize = animatedFontSize.sp
            ),
            singleLine = true
        )
    }
}