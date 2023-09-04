package com.bayarsahintekin.matchscores.util

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}