package com.canerture.valorantcompose.presentation.weapon.weapons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.canerture.valorantcompose.domain.model.Weapon
import com.canerture.valorantcompose.presentation.theme.ValoBlue
import com.canerture.valorantcompose.presentation.theme.ValoWhite
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeaponItem(
    weapon: Weapon,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onItemClick(weapon.uuid.orEmpty()) }
            .padding(12.dp),
        backgroundColor = ValoWhite
    ) {
        Box(contentAlignment = Alignment.Center) {
            GlideImage(
                imageModel = weapon.displayIcon,
                contentScale = ContentScale.Fit,
                circularReveal = CircularReveal(),
                modifier = Modifier
                    .size(150.dp)
            )

            Text(
                text = weapon.displayName.orEmpty(),
                style = MaterialTheme.typography.h5,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                ValoBlue
                            )
                        )
                    )
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}