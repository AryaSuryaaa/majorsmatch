package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.lerp
import com.anugrah.majorsmatch.data.remote.apiresponse.DataTestimony
import com.anugrah.majorsmatch.ui.theme.DIMENS_114dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun SliderBanner(
    modifier: Modifier = Modifier,
    testimony: List<DataTestimony>
) {
    val randomizedTestimony = remember(testimony) { testimony.shuffled().take(5) }

    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(Unit) {
        if (randomizedTestimony.isNotEmpty()) {
            while (true) {
                yield()
                delay(2600)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % pagerState.pageCount
                )
            }
        }
    }

    Column {
        HorizontalPager(
            count = randomizedTestimony.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = DIMENS_16dp),
            modifier = modifier
                .height(DIMENS_114dp)
                .fillMaxWidth()
        ) { page ->
            Card(
                shape = RoundedCornerShape(DIMENS_12dp),

                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(DIMENS_16dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(randomizedTestimony[page].fullName, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(DIMENS_8dp))
                    Text(randomizedTestimony[page].testimony, modifier = Modifier.weight(1f), overflow = TextOverflow.Ellipsis)
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(DIMENS_16dp),
            activeColor = MaterialTheme.colorScheme.onBackground,

        )
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun SliderBannerPreview() {
    SliderBanner(testimony = emptyList())
}