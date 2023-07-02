package com.example.utils.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.utils.R

@Composable
fun AvatarImage(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.dota2_icon_placeholder),
    url: String? = null,
    hasDotaPlus: Boolean,
) {
    Box(modifier) {
        ImageInBox(
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            painter = painter,
            url = url,
            contentDescription = "Player Avatar Image",
            contentPadding = PaddingValues(21.dp)
        )

        if (hasDotaPlus)
            ImageInBox(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .size(30.dp)
                    .background(color = MaterialTheme.colorScheme.inversePrimary),
                painter = painterResource(id = R.drawable.dota_plus_icon),
                contentDescription = "Player Has DotaPlus subscription",
                contentPadding = PaddingValues(5.dp)
            )
    }
}

@Composable
@Preview
private fun AvatarImage_Preview() {
    AvatarImage(
        painter = painterResource(id = R.drawable.dota2_icon_placeholder),
        hasDotaPlus = true
    )
}