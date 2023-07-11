package com.bayarsahintekin.matchscores.di

import com.bayarsahintekin.data.local.teams.TeamDao
import com.bayarsahintekin.data.local.teams.TeamsKeyDao
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.data.repository.TeamDataSource
import com.bayarsahintekin.data.repository.TeamRemoteDataSource
import com.bayarsahintekin.data.repository.TeamRemoteMediator
import com.bayarsahintekin.data.repository.TeamRepositoryImpl
import com.bayarsahintekin.data.repository.TeamsLocalDataSource
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.repository.TeamRepository
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
        teamRemoteMediator: TeamRemoteMediator
    ): TeamRepository {
        return TeamRepositoryImpl(teamRemote, teamLocal, teamRemoteMediator)
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

    @Provides
    @Singleton
    fun provideTeamMediator(
        teamLocalDataSource: TeamDataSource.Local,
        teamRemoteDataSource: TeamDataSource.Remote
    ): TeamRemoteMediator {
        return TeamRemoteMediator(teamLocalDataSource, teamRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTeamRemoveDataSource(scoreServices: ScoreServices): TeamDataSource.Remote {
        return TeamRemoteDataSource(scoreServices)
    }

    @Provides
    fun provideTeamsUseCase(teamRepository: TeamRepository): TeamsUseCase {
        return TeamsUseCase(teamRepository)
    }

    @Provides
    fun provideTeamUseCase(teamRepository: TeamRepository): TeamUseCase {
        return TeamUseCase(teamRepository)
    }
}