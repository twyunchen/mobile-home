package com.yunchen.m09.model

data class SlipResponse(
    val slip: Slip
)

data class Slip(
    val advice: String,
    val id: Int
)