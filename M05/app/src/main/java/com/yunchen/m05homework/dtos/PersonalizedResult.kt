package com.yunchen.m05homework.dtos

data class GetPersonalizedPlayListResult(
    val category: Int,
    val code: Int,
    val hasTaste: Boolean,
    val result: List<PersonalizedResult>
)

data class PersonalizedResult(
    val alg: String,
    val canDislike: Boolean,
    val copywriter: String,
    val highQuality: Boolean,
    val id: Long,
    val name: String,
    val picUrl: String,
    val playCount: Int,
    val trackCount: Int,
    val trackNumberUpdateTime: Long,
    val type: Int
)