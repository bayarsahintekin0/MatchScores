package com.bayarsahintekin.matchscores.di

import android.content.Context
import androidx.room.Room
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
    fun provideMovieRemoteKeyDao(movieDatabase: TeamsDatabase): TeamsKeyDao {
        return movieDatabase.teamRemoteKeysDao()
    }
}