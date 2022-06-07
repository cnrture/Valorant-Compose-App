package com.canerture.valorantcompose.di

import com.canerture.valorantcompose.data.remote.ValorantService
import com.canerture.valorantcompose.data.repository.ValorantRepositoryImpl
import com.canerture.valorantcompose.domain.repository.ValorantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideValorantRepository(valorantService: ValorantService): ValorantRepository {
        return ValorantRepositoryImpl(valorantService)
    }
}