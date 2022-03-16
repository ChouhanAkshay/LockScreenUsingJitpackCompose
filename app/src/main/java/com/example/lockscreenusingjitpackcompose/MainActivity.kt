package com.example.lockscreenusingjitpackcompose

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lockscreenusingjitpackcompose.ui.theme.LockScreenUsingJitpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LockScreenUsingJitpackComposeTheme {
                LockImage()
                TextSwipeToUnlock()
            }
        }
    }
}

//todo remove hardcoded values
@Composable
fun LockImage() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .background(shape = RoundedCornerShape(30.dp), color = Color.LightGray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painterResource(R.drawable.ic_lock), null,
                modifier = Modifier
                    .background(shape = RoundedCornerShape(30.dp), color = Color.White)
                    .height(45.dp)
                    .padding(5.dp)
                    .width(45.dp), alignment = Alignment.TopCenter
            )
        }
        TextSwipeToUnlock()
    }
}

@Composable
fun TextSwipeToUnlock() {
    Text(text = "Swipe to unlock", Modifier.padding(8.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LockScreenUsingJitpackComposeTheme {
        LockImage()
        TextSwipeToUnlock()
    }
}