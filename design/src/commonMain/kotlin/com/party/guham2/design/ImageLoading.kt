package com.party.guham2.design

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageLoading(
    modifier: Modifier,
    imageUrl: String? = null,
    borderColor: Color = GRAY100,
    borderWidth: Dp = 1.dp,
    roundedCornerShape: Dp = LARGE_CORNER_SIZE,
) {
    Card(
        modifier = modifier,
        border = BorderStroke(borderWidth, borderColor),
        shape = RoundedCornerShape(roundedCornerShape),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NetworkImageLoad(
                modifier = Modifier
                    .fillMaxSize(),
                url = imageUrl,
            )
        }
    }
}

@Composable
fun NetworkImageLoad(
    modifier: Modifier = Modifier,
    url: String? = null,
    radius: Dp = 16.dp,
) {

    SubcomposeAsyncImage(
        model = url,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(radius)),
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(DesignResources.Image.image_default),
                contentDescription = null
            )
        }
    )
}