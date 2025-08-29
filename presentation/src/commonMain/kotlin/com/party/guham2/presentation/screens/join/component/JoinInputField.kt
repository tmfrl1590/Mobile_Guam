package com.party.guham2.presentation.screens.join.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.party.guham2.design.component.input_field.InputField

@Composable
fun JoinInputField(
    modifier: Modifier = Modifier,
    inputText: String,
    placeHolder: String,
    borderColor: Color,
    enabled: Boolean,
    textStyle: TextStyle,
    containerColor: Color,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit = {},
    trailingIcon : @Composable (() -> Unit)? = {},
){
    InputField(
        modifier = modifier,
        inputText = inputText,
        placeHolder = placeHolder,
        borderColor = borderColor,
        textStyle = textStyle,
        enabled = enabled,
        containerColor = containerColor,
        onValueChange = onValueChange,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
    )
}