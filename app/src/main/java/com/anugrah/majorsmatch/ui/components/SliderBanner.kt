package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.anugrah.majorsmatch.data.remote.apiresponse.DataTestimony
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
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
    modifier: Modifier,
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
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            CardTestimony(
                page = page,
                pageOffset = pageOffset,
                testimony = randomizedTestimony,
                textOverflow = TextOverflow.Ellipsis
            )
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
    SliderBanner(testimony = emptyList(), modifier = Modifier)
}