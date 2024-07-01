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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kotlincourse.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.roundToInt

@Composable
fun ImageSlider(imageList: List<Int>) {
    var currentPage by remember { mutableStateOf(0) }
    val pageCount = imageList.size

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    val newPage = (currentPage - dragAmount / size.width).roundToInt()
                    currentPage = newPage.coerceIn(0, pageCount - 1)
                }
            }
    ) {
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(imageList) { index, image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(300.dp),
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
        R.drawable.icecream3
    )

    ImageSlider(imageList = images)
}

