package com.bayarsahintekin.matchscores.di

import com.bayarsahintekin.data.local.games.GameDao
import com.bayarsahintekin.data.local.games.GameRemoteKeysDao
import com.bayarsahintekin.data.local.players.PlayerDao
import com.bayarsahintekin.data.local.players.PlayersKeyDao
import com.bayarsahintekin.data.local.stats.StatsDao
import com.bayarsahintekin.data.local.stats.StatsRemoteKeysDao
import com.bayarsahintekin.data.local.teams.TeamDao
import com.bayarsahintekin.data.local.teams.TeamsKeyDao
import com.bayarsahintekin.data.remote.ScoreServices
import com.bayarsahintekin.data.repository.games.GameDataSource
import com.bayarsahintekin.data.repository.games.GameLocalDataSource
import com.bayarsahintekin.data.repository.games.GameRemoteDataSource
import com.bayarsahintekin.data.repository.games.GameRemoteMediators
import com.bayarsahintekin.data.repository.games.GameRepositoryImpl
import com.bayarsahintekin.data.repository.players.PlayerDataSource
import com.bayarsahintekin.data.repository.players.PlayerRemoteDataSource
import com.bayarsahintekin.data.repository.players.PlayerRepositoryImpl
import com.bayarsahintekin.data.repository.players.PlayerLocalDataSource
import com.bayarsahintekin.data.repository.players.PlayerRemoteMediators
import com.bayarsahintekin.data.repository.stats.StatsDataSource
import com.bayarsahintekin.data.repository.stats.StatsLocalDataSource
import com.bayarsahintekin.data.repository.stats.StatsRemoteDataSource
import com.bayarsahintekin.data.repository.stats.StatsRemoteMediators
import com.bayarsahintekin.data.repository.stats.StatsRepositoryImpl
import com.bayarsahintekin.data.repository.teams.TeamDataSource
import com.bayarsahintekin.data.repository.teams.TeamRemoteDataSource
import com.bayarsahintekin.data.repository.teams.TeamRepositoryImpl
import com.bayarsahintekin.data.repository.teams.TeamLocalDataSource
import com.bayarsahintekin.data.repository.teams.TeamRemoteMediator
import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.domain.repository.GameRepository
import com.bayarsahintekin.domain.repository.PlayersRepository
import com.bayarsahintekin.domain.repository.StatsRepository
import com.bayarsahintekin.domain.repository.TeamRepository
import com.bayarsahintekin.domain.usecase.FilterGamesUseCase
import com.bayarsahintekin.domain.usecase.GetGameByIdUseCase
import com.bayarsahintekin.domain.usecase.GetGameListUseCase
import com.bayarsahintekin.domain.usecase.GetPlayerByIdUseCase
import com.bayarsahintekin.domain.usecase.GetPlayerListUseCase
import com.bayarsahintekin.domain.usecase.GetTeamByIdUseCase
import com.bayarsahintekin.domain.usecase.SearchPlayerUseCase
import com.bayarsahintekin.domain.usecase.GetStatsByFilterUseCase
import com.bayarsahintekin.domain.usecase.GetTeamListUseCase
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
        return TeamRepositoryImpl(teamRemote, teamLocal,teamRemoteMediator)
    }

    @Provides
    @Singleton
    fun providePlayerRepository(
        playerRemote: PlayerDataSource.Remote,
        local: PlayerDataSource.Local,
        remoteMediators: PlayerRemoteMediators
    ): PlayersRepository {
        return PlayerRepositoryImpl(playerRemote,local,remoteMediators)
    }

    @Provides
    @Singleton
    fun provideGameRepository(
        remote: GameDataSource.Remote,
        local: GameDataSource.Local,
        remoteMediators: GameRemoteMediators
    ): GameRepository {
        return GameRepositoryImpl(remote,local,remoteMediators)
    }

    @Provides
    @Singleton
    fun provideStatRepository(
        remote: StatsDataSource.Remote,
        local: StatsDataSource.Local,
        remoteMediators: StatsRemoteMediators
    ): StatsRepository {
        return StatsRepositoryImpl(remote,local,remoteMediators)
    }

    @Provides
    @Singleton
    fun provideTeamLocalDataSource(
        executor: DiskExecutor,
        teamDao: TeamDao,
        teamKeyDao: TeamsKeyDao,
    ): TeamDataSource.Local {
        return TeamLocalDataSource(executor, teamDao, teamKeyDao)
    }

    @Provides
    @Singleton
    fun provideGameLocalDataSource(
        executor: DiskExecutor,
        gameDao: GameDao,
        gameRemoteKeysDao: GameRemoteKeysDao,
    ): GameDataSource.Local {
        return GameLocalDataSource(executor, gameDao, gameRemoteKeysDao)
    }

    @Provides
    @Singleton
    fun providePlayerLocalDataSource(executor: DiskExecutor,
                                     playerDao: PlayerDao,
                                     playerRemoteKeyDao: PlayersKeyDao,): PlayerDataSource.Local {
        return PlayerLocalDataSource(executor,playerDao,playerRemoteKeyDao )
    }

    @Provides
    @Singleton
    fun provideStatLocalDataSource(executor: DiskExecutor,
                                     statDao: StatsDao,
                                     statRemoteKeyDao: StatsRemoteKeysDao,): StatsDataSource.Local {
        return StatsLocalDataSource(executor,statDao,statRemoteKeyDao )
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
    fun providePlayersRemoteMediators(
        playerRemote: PlayerDataSource.Remote,
        playerLocal: PlayerDataSource.Local
    ): PlayerRemoteMediators {
        return PlayerRemoteMediators(playerLocal, playerRemote)
    }

    @Provides
    @Singleton
    fun provideGameRemoteMediators(
        gameRemote: GameDataSource.Remote,
        gameLocal: GameDataSource.Local
    ): GameRemoteMediators {
        return GameRemoteMediators(gameLocal, gameRemote)
    }

    @Provides
    @Singleton
    fun provideStatRemoteMediators(
        statsRemote: StatsDataSource.Remote,
        statsLocal: StatsDataSource.Local
    ): StatsRemoteMediators {
        return StatsRemoteMediators(statsLocal, statsRemote)
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
    fun provideGameRemoteDataSource(scoreServices: ScoreServices): GameDataSource.Remote {
        return GameRemoteDataSource(scoreServices)
    }

    @Provides
    @Singleton
    fun provideStatRemoteDataSource(scoreServices: ScoreServices): StatsDataSource.Remote {
        return StatsRemoteDataSource(scoreServices)
    }

    @Provides
    fun provideTeamsUseCase(teamRepository: TeamRepository): GetTeamListUseCase {
        return GetTeamListUseCase(teamRepository)
    }

    @Provides
    fun provideGetTeamByIdUseCase(teamRepository: TeamRepository): GetTeamByIdUseCase {
        return GetTeamByIdUseCase(teamRepository)
    }


    @Provides
    fun providePlayersUseCase(playersRepository: PlayersRepository): GetPlayerListUseCase {
        return GetPlayerListUseCase(playersRepository)
    }

    @Provides
    fun provideGetPlayerByIdUseCase(playersRepository: PlayersRepository): GetPlayerByIdUseCase {
        return GetPlayerByIdUseCase(playersRepository)
    }

    @Provides
    fun provideGamesUseCase(gamesRepository: GameRepository): GetGameListUseCase {
        return GetGameListUseCase(gamesRepository)
    }

    @Provides
    fun provideGameFiltersUseCase(gamesRepository: GameRepository): FilterGamesUseCase {
        return FilterGamesUseCase(gamesRepository)
    }

    @Provides
    fun provideStatsUseCase(statsRepository: StatsRepository): GetStatsByFilterUseCase {
        return GetStatsByFilterUseCase(statsRepository)
    }

    @Provides
    fun provideSearchPlayersUseCase(playersRepository: PlayersRepository): SearchPlayerUseCase {
        return SearchPlayerUseCase(playersRepository)
    }

    @Provides
    fun provideGetGameByIdUseCase(gameRepository: GameRepository): GetGameByIdUseCase {
        return GetGameByIdUseCase(gameRepository)
    }
}