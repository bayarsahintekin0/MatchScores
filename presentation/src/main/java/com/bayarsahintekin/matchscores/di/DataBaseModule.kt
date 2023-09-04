package com.bayarsahintekin.matchscores.di

import android.content.Context
import androidx.room.Room
import com.bayarsahintekin.data.local.games.GameDao
import com.bayarsahintekin.data.local.games.GameDataBase
import com.bayarsahintekin.data.local.games.GameRemoteKeysDao
import com.bayarsahintekin.data.local.players.PlayerDao
import com.bayarsahintekin.data.local.players.PlayersDataBase
import com.bayarsahintekin.data.local.players.PlayersKeyDao
import com.bayarsahintekin.data.local.stats.StatsDao
import com.bayarsahintekin.data.local.stats.StatsDataBase
import com.bayarsahintekin.data.local.stats.StatsRemoteKeysDao
import com.bayarsahintekin.data.local.teams.TeamDao
import com.bayarsahintekin.data.local.teams.TeamsDatabase
import com.bayarsahintekin.data.local.teams.TeamsKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideTeamDatabase(@ApplicationContext context: Context): TeamsDatabase {
        return Room.databaseBuilder(context, TeamsDatabase::class.java, "teams.db").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTeamDao(teamsDatabase: TeamsDatabase): TeamDao {
        return teamsDatabase.teamDao()
    }

    @Provides
    fun provideMovieRemoteKeyDao(teamDatabase: TeamsDatabase): TeamsKeyDao {
        return teamDatabase.teamRemoteKeysDao()
    }

    /**
     * Players
     */
    @Provides
    @Singleton
    fun providePlayerDatabase(@ApplicationContext context: Context): PlayersDataBase {
        return Room.databaseBuilder(context, PlayersDataBase::class.java, "players.db").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePlayerDao(playersDataBase: PlayersDataBase): PlayerDao {
        return playersDataBase.playerDao()
    }

    @Provides
    fun providePlayerRemoteKeyDao(playersDataBase: PlayersDataBase): PlayersKeyDao {
        return playersDataBase.playerKeyDao()
    }

    /**
     * Games
     */
    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext context: Context): GameDataBase {
        return Room.databaseBuilder(context, GameDataBase::class.java, "games.db").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideGameDao(gamesDataBase: GameDataBase): GameDao {
        return gamesDataBase.gamesDao()
    }

    @Provides
    fun provideGameRemoteKeyDao(gameDataBase: GameDataBase): GameRemoteKeysDao {
        return gameDataBase.gameKeysDao()
    }

    /**
     * Stats
     */
    @Provides
    @Singleton
    fun provideStatsDatabase(@ApplicationContext context: Context): StatsDataBase {
        return Room.databaseBuilder(context, StatsDataBase::class.java, "stats.db").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideStatsDao(statsDataBase: StatsDataBase): StatsDao {
        return statsDataBase.statsDao()
    }

    @Provides
    fun provideStatsRemoteKeyDao(statsDataBase: StatsDataBase): StatsRemoteKeysDao {
        return statsDataBase.statsKeyDao()
    }
}