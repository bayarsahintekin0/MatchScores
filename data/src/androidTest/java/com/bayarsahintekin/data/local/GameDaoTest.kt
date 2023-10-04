package com.bayarsahintekin.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.bayarsahintekin.data.data.GamesMockData
import com.bayarsahintekin.data.entity.games.toDomain
import com.bayarsahintekin.data.local.games.GameDataBase
import com.bayarsahintekin.data.repository.games.GameDataSource
import com.bayarsahintekin.domain.utils.getResult
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class GameDaoTest {

    private lateinit var database: GameDataBase

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var local: GameDataSource.Local


    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GameDataBase::class.java
        ).allowMainThreadQueries().build()

        hiltRule.inject()

    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun saveGames_returnsTrue() = runTest {
        local.saveGames(listOf(GamesMockData.getGame().toDomain())).let {
            local.getGames().let {
                it.getResult({
                    assertThat(it.data, Matchers.hasItem(GamesMockData.getGame().toDomain()))
                }, {

                })

            }
        }

    }

    @Test
    fun clearGames_returnsTrue() = runTest {
        local.saveGames(listOf(GamesMockData.getGame().toDomain())).let {
            local.getGames().let {
                it.getResult({
                    assertThat(
                        "Database is not empty check",
                        it.data,
                        Matchers.hasItem(GamesMockData.getGame().toDomain())
                    )
                    local.clearGames().let {
                        local.getGames().let { result ->
                            result.getResult({
                                assertTrue(it.data.isEmpty())
                            }, {

                            })

                        }
                    }
                }, {

                })
            }
        }

    }

    @Test
    fun getGame_returnsTrue() = runTest {
        local.saveGames(listOf(GamesMockData.getGame().toDomain())).let {
            local.getGame(18).getResult({
                assertEquals(18, it.data.id)
            }, {

            })
        }
    }

    @Test
    fun getGameError_returnsTrue() = runTest {
        local.saveGames(listOf(GamesMockData.getGame().toDomain())).let {
            local.getGame(18).getResult({
                assertNotSame(1400, it.data.id)
            }, {

            })
        }
    }

    @Test
    fun getGames_returnsTrue() = runTest {
        val mockList = GamesMockData.getGames()
        local.clearGames().let {
            local.saveGames(mockList).let {
                local.getGames().getResult({
                    assertEquals(mockList.size, it.data.size)
                }, {

                })
            }
        }
    }
}