package com.example.kotlincourse.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlincourse.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ImageSlider(imageList: List<Int>) {
    var currentPage by remember { mutableStateOf(0) }
    val pageCount = imageList.size
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // LaunchedEffect to update the currentPage automatically
    LaunchedEffect(currentPage) {
        while (true) {
            delay(3000) // Delay of 3 seconds
            currentPage = (currentPage + 1) % pageCount
            coroutineScope.launch {
                listState.animateScrollToItem(currentPage)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    val newPage = (currentPage - dragAmount / size.width).roundToInt()
                    currentPage = newPage.coerceIn(0, pageCount - 1)
                    coroutineScope.launch {
                        listState.animateScrollToItem(currentPage)
                    }
                }
            }
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            itemsIndexed(imageList) { index, image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(230.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 0 until pageCount) {
                Box(
                    modifier = Modifier
                        .size(if (i == currentPage) 12.dp else 8.dp)
                        .padding(2.dp)
                        .background(
                            color = if (i == currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
fun ImageSliderPreview() {
    val images = listOf(
        R.drawable.icecream1,
        R.drawable.icecream2,
        R.drawable.icecream3,
        R.drawable.icecream4,

    )

    ImageSlider(imageList = images)
}

@Preview
@Composable
fun PreviewImageSlider() {
    MaterialTheme {
        Surface {
            ImageSliderPreview()
        }
    }
}
