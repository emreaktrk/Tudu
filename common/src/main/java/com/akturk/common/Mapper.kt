package com.akturk.common

interface Mapper<I, O> {
    fun transform(item: I): O
}