package com.akturk.domain

interface IUseCase<T> {
    suspend fun invoke(delegate: T)
}