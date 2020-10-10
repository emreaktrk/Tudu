package com.akturk.domain.model


data class TodoItem(
    val title: String?,
    val description: String?,
    val latLng: Pair<Double, Double>?,
    val tags: MutableSet<String> = mutableSetOf(),
)