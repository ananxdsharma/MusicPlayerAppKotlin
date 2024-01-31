package com.example.musicapp

data class MusicSearch(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)