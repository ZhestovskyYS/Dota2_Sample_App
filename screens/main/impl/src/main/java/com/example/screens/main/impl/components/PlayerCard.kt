package com.example.screens.main.impl.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impl.R
import com.example.screens.main.api.data.Player

@Composable
@ExperimentalMaterial3Api
internal fun PlayerCard(
    player: Player,
    modifier: Modifier = Modifier,
    onClick: (id: String) -> Unit = {},
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(size = 12.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        onClick = { onClick("") },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            player.avatar?.asImageBitmap()?.let { imageBitmap ->
                Image(
                    modifier = Modifier.size(50.dp),
                    bitmap = imageBitmap,
                    contentDescription = ""
                )
            } ?: Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.dota2_logo_icon),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = "Nickname",
                    maxLines = 1,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterial3Api
fun PlayerCardPreview() {
    MaterialTheme {
        PlayerCard(
            modifier = Modifier
                .width(335.dp)
                .height(80.dp),
            player = Player(
                id = "123",
                nickname = "Sergey-Kostyan",
                avatar = BitmapFactory.decodeResource(
                    LocalContext.current.resources,
                    R.drawable.dota2_logo_icon
                )
            )
        )
    }
}