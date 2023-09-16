package com.bayarsahintekin.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.toDomain
import com.bayarsahintekin.data.entity.teams.TeamsDbData
import com.bayarsahintekin.data.local.players.PlayerDao
import com.bayarsahintekin.data.local.players.PlayersDataBase
import com.bayarsahintekin.data.repository.players.PlayerDataSource
import com.bayarsahintekin.domain.utils.getResult
import com.google.common.base.Predicates.contains
import com.google.common.base.Predicates.equalTo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class PlayerDaoTest {

    private lateinit var database: PlayersDataBase
   // private lateinit var playerDao: PlayerDao

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
        val player = PlayersDbData(
            id = 14,
            firstName = "Ike",
            heightFeet = "0",
            heightInches = "0",
            lastName = "Anigbogu",
            position = "C",
            team = TeamsDbData(
                id = 12,
                abbreviation = "IND",
                city = "Indiana",
                conference = "East",
                division = "Central",
                fullName = "Indiana Pacers",
                name = "Pacers"
            ),
            weightPounds = "0"
        )

        local.savePlayers(listOf(player.toDomain())).let {
            local.getPlayers().let {
                it.getResult({
                    assertThat(it.data, Matchers.hasItem(player.toDomain()))
                },{

                })

            }
        }





    }
}