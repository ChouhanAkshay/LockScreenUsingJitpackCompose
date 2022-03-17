package com.example.lockscreenusingjitpackcompose.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.lockscreenusingjitpackcompose.R
import com.example.lockscreenusingjitpackcompose.data.models.ListOfBackgroundImages
import com.example.lockscreenusingjitpackcompose.ui.viewmodels.MainViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LockScreenPageBody(onUnlocked: () -> Unit = {}, viewModel: MainViewModel? = null) {
    viewModel?.apply {
        setIsSuffle(true)
        startImageSuffle()
    }

    val swipebleState = rememberSwipeableState(0)
    val isUnlocked = remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    //calculate maximum swipe distance
    val swipeToSize = with(LocalDensity.current) {
        screenWidth.toPx() - (60.dp.toPx()) - (45.dp.toPx())
    }

    //set map of anchors for swipe
    val anchors = mapOf(0f to 0, swipeToSize to 1)

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel?.apply {
            BackgroundImages(viewModel = viewModel)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .background(shape = RoundedCornerShape(30.dp), color = Color.LightGray)
                .swipeable(
                    state = swipebleState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painterResource(R.drawable.ic_lock),
                null,
                modifier = Modifier
                    .offset { IntOffset(swipebleState.offset.value.roundToInt(), 0) }
                    .background(shape = RoundedCornerShape(30.dp), color = Color.White)
                    .height(45.dp)
                    .width(45.dp)
                    .padding(5.dp)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            swipebleState.performDrag(dragAmount)
                        }
                    }
                    .onGloballyPositioned { layoutCoordinates ->
                        if (layoutCoordinates.positionInWindow().x >= swipeToSize && !isUnlocked.value) {
                            onUnlocked()
                            viewModel?.setIsSuffle(false)
                            isUnlocked.value = true
                        }
                    },
                alignment = Alignment.TopCenter,
            )
        }
        TextSwipeToUnlock()
    }
}

@Composable
fun BackgroundImages(viewModel: MainViewModel) {

    val targetState : Int by viewModel.currentImagePosition.observeAsState(0)

    Crossfade(targetState = targetState, animationSpec = tween(1000)) { state ->
        Box(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .size(300.dp)
        ) {
            Image(
                painter = painterResource(id = ListOfBackgroundImages().images[state]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
            )
        }
    }
}

@Composable
fun TextSwipeToUnlock() {
    Text(text = "Swipe to unlock", Modifier.padding(8.dp))
}

@Preview(showBackground = true)
@Composable
fun previewBackgroundImages() {
    LockScreenPageBody()
}
