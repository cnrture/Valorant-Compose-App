package com.canerture.valorantcompose.presentation.weapon.weapondetail

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canerture.valorantcompose.R
import com.canerture.valorantcompose.data.model.weapons.Skin
import com.canerture.valorantcompose.presentation.theme.ValoBlue
import com.canerture.valorantcompose.presentation.theme.ValoLightBlue
import com.canerture.valorantcompose.presentation.theme.ValoRed
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun WeaponDetailScreen(
    weaponUuid: String,
    viewModel: WeaponDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getWeaponDetail(weaponUuid)
    }

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        state.weapon?.let {

            Box(
                modifier = Modifier
                    .background(
                        color = ValoRed,
                        shape = RoundedCornerShape(
                            bottomStart = 32.dp,
                            bottomEnd = 32.dp
                        )
                    )
                    .padding(24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    GlideImage(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 24.dp),
                        imageModel = it.displayIcon,
                        circularReveal = CircularReveal(),
                        contentDescription = stringResource(R.string.desc_weapon_image)
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    Text(
                        text = it.displayName,
                        style = MaterialTheme.typography.h4
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = it.category.replace("EEquippableCategory::", ""),
                        style = MaterialTheme.typography.h5
                    )
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = stringResource(R.string.title_damage_range),
                color = Color.White,
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.size(16.dp))

            it.weaponStats?.damageRanges?.getOrNull(0)?.let { damageRange ->
                Column(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                ) {
                    LinearProgress(
                        header = stringResource(R.string.text_head),
                        progress = (damageRange.headDamage / 200).toFloat(),
                        progressString = damageRange.headDamage.toString()
                    )

                    LinearProgress(
                        header = stringResource(R.string.text_body),
                        progress = (damageRange.bodyDamage.toFloat() / 200),
                        progressString = damageRange.bodyDamage.toString()
                    )

                    LinearProgress(
                        header = stringResource(R.string.text_leg),
                        progress = (damageRange.legDamage / 200).toFloat(),
                        progressString = damageRange.legDamage.toString()
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = stringResource(R.string.title_skins),
                color = Color.White,
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.size(16.dp))

            TabLayout(it.skins)
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(skinList: List<Skin>) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Card(
        backgroundColor = ValoLightBlue,
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
    ) {
        Column {

            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                edgePadding = 0.dp,
            ) {
                skinList.forEachIndexed { tabIndex, skin ->
                    val color = remember {
                        Animatable(ValoRed)
                    }

                    LaunchedEffect(
                        pagerState.currentPage == tabIndex
                    ) {
                        color.animateTo(if (pagerState.currentPage == tabIndex) ValoRed else ValoBlue)
                    }

                    Tab(
                        selected = pagerState.currentPage == tabIndex,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(tabIndex)
                            }
                        },
                        icon = {
                            GlideImage(
                                imageModel = skin.displayIcon,
                                circularReveal = CircularReveal(),
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(76.dp)
                            )
                        },
                        modifier = Modifier
                            .background(color = color.value),
                    )
                }
            }

            Spacer(modifier = Modifier.size(12.dp))

            HorizontalPager(
                count = skinList.size,
                state = pagerState
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    GlideImage(
                        imageModel = skinList[page].displayIcon,
                        circularReveal = CircularReveal(),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 32.dp, vertical = 16.dp)
                    )

                    Text(
                        text = skinList[page].displayName,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LinearProgress(header: String, progress: Float, progressString: String) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$header - $progressString",
            style = MaterialTheme.typography.h5,
            color = ValoRed,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(4.dp))
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier.height(10.dp),
            color = ValoRed,
            backgroundColor = Color.White
        )
    }
}