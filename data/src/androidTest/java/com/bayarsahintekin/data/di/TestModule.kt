package com.bayarsahintekin.data.di

import android.content.Context
import androidx.room.Room
import com.bayarsahintekin.data.local.games.GameDao
import com.bayarsahintekin.data.local.games.GameDataBase
import com.bayarsahintekin.data.local.games.GameRemoteKeysDao
import com.bayarsahintekin.data.local.players.PlayerDao
import com.bayarsahintekin.data.local.players.PlayersDataBase
import com.bayarsahintekin.data.local.players.PlayersKeyDao
import com.bayarsahintekin.data.repository.games.GameDataSource
import com.bayarsahintekin.data.repository.games.GameLocalDataSource
import com.bayarsahintekin.data.repository.players.PlayerDataSource
import com.bayarsahintekin.data.repository.players.PlayerLocalDataSource
import com.bayarsahintekin.data.utils.DiskExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun providePlayerLocalDataSource(executor: DiskExecutor,
                                     playerDao: PlayerDao,
                                     playerRemoteKeyDao: PlayersKeyDao,): PlayerDataSource.Local {
        return PlayerLocalDataSource(executor,playerDao,playerRemoteKeyDao )
    }

    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    @Provides
    fun providePlayerDao(playersDataBase: PlayersDataBase): PlayerDao {
        return playersDataBase.playerDao()
    }

    @Provides
    fun providePlayerRemoteKeyDao(playersDataBase: PlayersDataBase): PlayersKeyDao {
        return playersDataBase.playerKeyDao()
    }

    @Provides
    @Singleton
    fun providePlayerDatabase(@ApplicationContext context: Context): PlayersDataBase {
        return Room.databaseBuilder(context, PlayersDataBase::class.java, "players.db").fallbackToDestructiveMigration().build()
    }

    /**
     * Game
     */

    @Provides
    @Singleton
    fun provideGameLocalDataSource(executor: DiskExecutor,
                                     gameDao: GameDao,
                                     gameRemoteKeysDao: GameRemoteKeysDao,): GameDataSource.Local {
        return GameLocalDataSource(executor,gameDao,gameRemoteKeysDao )
    }


    @Provides
    fun provideGameDao(gameDataBase: GameDataBase): GameDao {
        return gameDataBase.gamesDao()
    }

    @Provides
    fun provideGameRemoteKeyDao(gameDataBase: GameDataBase): GameRemoteKeysDao {
        return gameDataBase.gameKeysDao()
    }

    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext context: Context): GameDataBase {
        return Room.databaseBuilder(context, GameDataBase::class.java, "games.db").fallbackToDestructiveMigration().build()
    }
}