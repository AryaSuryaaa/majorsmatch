package com.anugrah.majorsmatch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.lerp
import com.anugrah.majorsmatch.data.dummy.universitasList
import com.anugrah.majorsmatch.ui.theme.DIMENS_114dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_12dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_16dp
import com.anugrah.majorsmatch.ui.theme.DIMENS_8dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun SliderBanner(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val universitys = universitasList

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column {
        HorizontalPager(
            count = universitys.size,
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
                    Text(universitys[page].nama, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(DIMENS_8dp))
                    Text(universitys[page].deskripsi, modifier = Modifier.weight(1f), overflow = TextOverflow.Ellipsis)
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
    SliderBanner()
}