package com.bayarsahintekin.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.bayarsahintekin.data.data.PlayersMockData
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.local.players.PlayersDataBase
import com.bayarsahintekin.data.repository.players.PlayerDataSource
import com.bayarsahintekin.domain.utils.getResult
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * getPlayer
 * savePlayer
 * getPlayers
 * savePlayers
 *
 * tests applied
 */

@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class PlayerDaoTest {

    private lateinit var database: PlayersDataBase

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var local:PlayerDataSource.Local


    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PlayersDataBase::class.java
        ).allowMainThreadQueries().build()

        hiltRule.inject()

    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun savePlayer_returnsTrue() = runTest {
        local.savePlayers(listOf(PlayersMockData.getPlayer().toDomain())).let {
            local.getPlayers().let {
                it.getResult({
                    assertThat(it.data, Matchers.hasItem(PlayersMockData.getPlayer().toDomain()))
                },{

                })

            }
        }

    }

    @Test
    fun clearPlayer_returnsTrue() = runTest {
        local.savePlayers(listOf(PlayersMockData.getPlayer().toDomain())).let {
            local.getPlayers().let {
                it.getResult({
                    assertThat("Database is not empty check",it.data, Matchers.hasItem(PlayersMockData.getPlayer().toDomain()))
                    local.clearPlayers().let {
                       local.getPlayers().let { result ->
                           result.getResult({
                                  assertTrue(it.data.isEmpty())
                           },{

                           })

                       }
                    }
                },{

                })
            }
        }

    }

    @Test
    fun getPlayer_returnsTrue() = runTest{
        local.savePlayers(listOf(PlayersMockData.getPlayer().toDomain())).let {
            local.getPlayer(14.toString()).getResult({
                assertEquals(14,it.data.id)
            },{

            })
        }
    }

    @Test
    fun getPlayerError_returnsTrue() = runTest{
        local.savePlayers(listOf(PlayersMockData.getPlayer().toDomain())).let {
            local.getPlayer(14.toString()).getResult({
                assertNotSame(1400,it.data.id)
            },{

            })
        }
    }

    @Test
    fun getPlayers_returnsTrue() = runTest{
        val mockList = PlayersMockData.getPlayers()
        local.clearPlayers().let {
            local.savePlayers(mockList).let {
                local.getPlayers().getResult({
                    assertEquals(mockList,it.data)
                },{

                })
            }
        }
    }
}