package com.party.guham2.design.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.party.guham2.design.LARGE_CORNER_SIZE
import com.party.guham2.design.LIGHT400
import com.party.guham2.design.PRIMARY
import com.party.guham2.design.WHITE
import com.party.guham2.design.component.button.CustomButton
import com.party.guham2.design.component.dialog.component.DialogDescriptionSection
import com.party.guham2.design.component.dialog.component.DialogTitleSection
import com.party.guham2.design.component.util.HeightSpacer

@Composable
fun TwoButtonDialog(
    dialogTitle: String,
    description: String,
    cancelButtonText: String,
    confirmButtonText: String,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
){
    Dialog(
        onDismissRequest = {
            onCancel()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
    ) {
        Card(
            modifier = Modifier
                .width(312.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(LARGE_CORNER_SIZE),
            colors = CardDefaults.cardColors(
                containerColor = WHITE
            ),
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight(),
                ) {
                    HeightSpacer(heightDp = 32.dp)
                    DialogTitleSection(
                        dialogTitle = dialogTitle
                    )
                    DialogDescriptionSection(
                        description = description
                    )
                }

                HeightSpacer(32.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomButton(
                        modifier = Modifier
                            .weight(0.5f)
                        ,
                        buttonText = cancelButtonText,
                        containerColor = LIGHT400,
                        borderColor = LIGHT400,
                        radius = 0.dp,
                        buttonTextSize = 14.sp,
                        buttonTextWeight = FontWeight.Bold,
                        onClick = onCancel
                    )
                    CustomButton(
                        modifier = Modifier
                            .weight(0.5f)
                        ,
                        buttonText = confirmButtonText,
                        containerColor = PRIMARY,
                        borderColor = PRIMARY,
                        radius = 0.dp,
                        buttonTextSize = 14.sp,
                        buttonTextWeight = FontWeight.Bold,
                        onClick = onConfirm
                    )
                }
            }
        }
    }
}