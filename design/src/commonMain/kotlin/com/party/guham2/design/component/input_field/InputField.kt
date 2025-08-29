package com.party.guham2.design.component.input_field

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.party.guham2.design.GRAY500
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.util.calculateLetterSpacing
import io.ktor.websocket.Frame

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    inputText: String,
    placeHolder: String,
    placeHolderColor: Color = GRAY500,
    borderColor: Color,
    borderWidth: Dp = 1.dp,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    radius: Dp = LARGE_CORNER_SIZE,
    textStyle: TextStyle,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    containerColor: Color,
    trailingIcon : @Composable (() -> Unit)? = {},
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier
            .background(containerColor, shape = RoundedCornerShape(radius))
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(radius)
            )
        ,
        visualTransformation = visualTransformation,
        value = inputText,
        onValueChange = { onValueChange(it) },
        readOnly = readOnly,
        enabled = enabled,
        maxLines = maxLines,
        singleLine = singleLine,
        textStyle = textStyle,
        cursorBrush = SolidColor(Color.Black),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                ,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(
                    modifier = Modifier
                        .width(width = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .wrapContentHeight()
                    ,
                ){
                    innerTextField()
                    if(inputText.isEmpty()){
                        Text(
                            text = placeHolder,
                            style = TextStyle(
                                color = placeHolderColor,
                                fontSize = 16.sp,
                                letterSpacing = calculateLetterSpacing(16.sp, -2.5f)
                            )
                        )
                    }
                }
                trailingIcon?.invoke()

                Spacer(
                    modifier = Modifier
                        .width(width = 16.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        keyboardActions = keyboardActions,
    )
}