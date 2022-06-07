package com.canerture.valorantcompose.presentation.main

import androidx.annotation.NonNull
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.canerture.valorantcompose.R
import com.canerture.valorantcompose.common.Constants
import com.canerture.valorantcompose.navigation.Screen
import com.canerture.valorantcompose.presentation.theme.ValoRed
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun MainScreen(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {

        ComposeYoutubePlayer("e7VOQ1l20eo")

        DrawCategoryItem(
            R.drawable.ic_agents,
            R.drawable.ic_maps,
            Screen.Agents.route,
            Screen.Maps.route,
            Constants.CATEGORY_AGENTS,
            Constants.CATEGORY_MAPS,
            navController
        )

        Spacer(modifier = Modifier.size(20.dp))

        DrawCategoryItem(
            R.drawable.ic_weapons,
            R.drawable.ic_tiers,
            Screen.Weapons.route,
            Screen.CompetitiveTiers.route,
            Constants.CATEGORY_WEAPONS,
            Constants.CATEGORY_COMPETITIVE_TIERS,
            navController
        )
    }
}

@Composable
fun DrawCategoryItem(
    logoOne: Int,
    logoTwo: Int,
    routeOne: String,
    routeTwo: String,
    categoryOne: String,
    categoryTwo: String,
    navController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .clickable { navController.navigate(route = routeOne) }
                .background(color = ValoRed, shape = RoundedCornerShape(24.dp))
                .size(150.dp)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(logoOne),
                contentDescription = routeOne,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = categoryOne,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Column(
            modifier = Modifier
                .clickable { navController.navigate(route = routeTwo) }
                .background(color = ValoRed, shape = RoundedCornerShape(24.dp))
                .size(150.dp)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(logoTwo),
                contentDescription = routeTwo,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = categoryTwo,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ComposeYoutubePlayer(videoId: String) {

    val context = LocalContext.current

    val youtubePlayerView = remember {
        YouTubePlayerView(context).apply {
            id = R.id.player_view
        }
    }

    youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
            youTubePlayer.cueVideo(
                videoId = videoId,
                startSeconds = 0f
            )
        }
    })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 32.dp,
                vertical = 64.dp
            )
    ) {
        AndroidView(
            factory = { youtubePlayerView }
        )
    }
}