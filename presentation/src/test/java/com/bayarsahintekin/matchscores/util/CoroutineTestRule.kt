package com.bayarsahintekin.matchscores.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule : TestWatcher() {

    internal val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

    val testDispatcherProvider = object : DispatchersProvider {
        override fun getIO(): CoroutineDispatcher = testDispatcher
        override fun getMain(): CoroutineDispatcher = testDispatcher
        override fun getMainImmediate(): CoroutineDispatcher = testDispatcher
        override fun getDefault(): CoroutineDispatcher = testDispatcher
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineTestRule.runTest(block: suspend TestScope.() -> Unit) = runTest(testDispatcher) {
    block()
}
