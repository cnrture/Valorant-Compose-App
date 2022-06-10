package com.canerture.valorantcompose.presentation.agent.agentdetail

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canerture.valorantcompose.R
import com.canerture.valorantcompose.common.components.ErrorText
import com.canerture.valorantcompose.common.components.HeaderText
import com.canerture.valorantcompose.data.model.agents.Ability
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
fun AgentDetailScreen(
    agentUuid: String,
    viewModel: AgentDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAgentDetail(agentUuid)
    }

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        state.agent?.let {

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
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = it.role.displayIcon,
                    circularReveal = CircularReveal(),
                    alpha = 0.2f
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    GlideImage(
                        modifier = Modifier.size(300.dp),
                        imageModel = it.fullPortraitV2,
                        circularReveal = CircularReveal(),
                        contentDescription = stringResource(R.string.desc_agent_image)
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    Text(
                        text = it.displayName,
                        style = MaterialTheme.typography.h4
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = it.role.displayName,
                        style = MaterialTheme.typography.h5
                    )
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            HeaderText(header = stringResource(R.string.title_description))

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp
                ),
                text = it.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.size(24.dp))

            HeaderText(header = stringResource(R.string.title_abilities))

            Spacer(modifier = Modifier.size(8.dp))

            TabLayout(it.abilities)
        }

        if (state.error.isNotBlank()) ErrorText(
            state.error,
            Modifier.align(Alignment.CenterHorizontally)
        )

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
fun TabLayout(
    abilities: List<Ability>
) {

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
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = ValoLightBlue,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .tabIndicatorOffset(
                                tabPositions[pagerState.currentPage]
                            )
                            .width(0.dp)
                            .height(0.dp)
                    )
                }
            ) {
                abilities.forEachIndexed { index, ability ->
                    val color = remember {
                        Animatable(ValoRed)
                    }

                    LaunchedEffect(
                        pagerState.currentPage == index
                    ) {
                        color.animateTo(if (pagerState.currentPage == index) ValoRed else ValoBlue)
                    }
                    Tab(
                        icon = {
                            GlideImage(
                                imageModel = ability.displayIcon,
                                circularReveal = CircularReveal(),
                                contentScale = ContentScale.Fit,
                                colorFilter = if (pagerState.currentPage == index) ColorFilter.tint(
                                    Color.White
                                )
                                else ColorFilter.tint(Color.Gray),
                                modifier = Modifier.size(32.dp)
                            )
                        },
                        selected = pagerState.currentPage == index,
                        modifier = Modifier
                            .background(color = color.value),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.size(12.dp))

            HorizontalPager(
                count = abilities.size,
                state = pagerState
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = abilities[page].displayName,
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = abilities[page].description,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}