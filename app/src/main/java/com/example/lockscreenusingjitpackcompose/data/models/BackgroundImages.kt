package com.example.lockscreenusingjitpackcompose.data.models

import com.example.lockscreenusingjitpackcompose.R

data class ListOfBackgroundImages(
    val images: List<Int> = listOf(
        R.drawable.ic_bird,
        R.drawable.ic_cati,
        R.drawable.ic_flower,
        R.drawable.ic_dianosors,
        R.drawable.ic_raindeer
    )
)