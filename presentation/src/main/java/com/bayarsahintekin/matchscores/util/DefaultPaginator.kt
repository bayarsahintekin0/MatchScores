package com.bayarsahintekin.matchscores.util

import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect


class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> List<Item>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        /*result.catch {
            onError(it)
            onLoadUpdated(false)
        }.collect {
            items = it
        }
        result.collect{
            items = it.toList()
        }.runCatching {
            onError(it)
            onLoadUpdated(false)
        }
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }*/
        currentKey = getNextKey(result)
        onSuccess(result, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}