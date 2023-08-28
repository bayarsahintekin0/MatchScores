package com.bayarsahintekin.matchscores.di

import com.bayarsahintekin.data.local.players.PlayerDao
import com.bayarsahintekin.data.local.players.PlayersKeyDao
import com.bayarsahintekin.data.local.teams.TeamDao
import com.bayarsahintekin.data.local.teams.TeamsKeyDao
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.data.repository.PlayerDataSource
import com.bayarsahintekin.data.repository.PlayerRemoteDataSource
import com.bayarsahintekin.data.repository.PlayerRepositoryImpl
import com.bayarsahintekin.data.repository.PlayersLocalDataSource
import com.bayarsahintekin.data.repository.PlayersRemoteMediators
import com.bayarsahintekin.data.repository.TeamDataSource
import com.bayarsahintekin.data.repository.TeamRemoteDataSource
import com.bayarsahintekin.data.repository.TeamRepositoryImpl
import com.bayarsahintekin.data.repository.TeamsLocalDataSource
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.usecase.PlayersUseCase
import com.bayarsahintekin.domain.usecase.TeamUseCase
import com.bayarsahintekin.domain.usecase.TeamsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideTeamRepository(
        teamRemote: TeamDataSource.Remote,
        teamLocal: TeamDataSource.Local,
        //teamRemoteMediator: TeamRemoteMediator
    ): TeamRepository {
        return TeamRepositoryImpl(teamRemote, teamLocal)
    }

    @Provides
    @Singleton
    fun providePlayerRepository(
        playerRemote: PlayerDataSource.Remote,
        local: PlayerDataSource.Local,
        remoteMediators: PlayersRemoteMediators
    ): PlayersRepository {
        return PlayerRepositoryImpl(playerRemote,local,remoteMediators)
    }

    @Provides
    @Singleton
    fun provideTeamLocalDataSource(
        executor: DiskExecutor,
        teamDao: TeamDao,
        teamKeyDao: TeamsKeyDao,
    ): TeamDataSource.Local {
        return TeamsLocalDataSource(executor, teamDao, teamKeyDao)
    }

   /* @Provides
    @Singleton
    fun provideTeamMediator(
        teamLocalDataSource: TeamDataSource.Local,
        teamRemoteDataSource: TeamDataSource.Remote
    ): TeamRemoteMediator {
        return TeamRemoteMediator(teamLocalDataSource, teamRemoteDataSource)
    }*/

    @Provides
    @Singleton
    fun providePlayersRemoteMediators(
        playerRemote: PlayerDataSource.Remote,
        playerLocal: PlayerDataSource.Local
    ): PlayersRemoteMediators {
        return PlayersRemoteMediators(playerLocal, playerRemote)
    }

    @Provides
    @Singleton
    fun provideTeamRemoteDataSource(scoreServices: ScoreServices): TeamDataSource.Remote {
        return TeamRemoteDataSource(scoreServices)
    }

    @Provides
    @Singleton
    fun providePlayerRemoteDataSource(scoreServices: ScoreServices): PlayerDataSource.Remote {
        return PlayerRemoteDataSource(scoreServices)
    }

    @Provides
    @Singleton
    fun providePlayerLocalDataSource(executor: DiskExecutor,
                                     playerDao: PlayerDao,
                                     playerRemoteKeyDao: PlayersKeyDao,): PlayerDataSource.Local {
        return PlayersLocalDataSource(executor,playerDao,playerRemoteKeyDao )
    }

    @Provides
    fun provideTeamsUseCase(teamRepository: TeamRepository): TeamsUseCase {
        return TeamsUseCase(teamRepository)
    }

    @Provides
    fun provideTeamUseCase(teamRepository: TeamRepository): TeamUseCase {
        return TeamUseCase(teamRepository)
    }


    @Provides
    fun providePlayersUseCase(playersRepository: PlayersRepository): PlayersUseCase {
        return PlayersUseCase(playersRepository)
    }
}