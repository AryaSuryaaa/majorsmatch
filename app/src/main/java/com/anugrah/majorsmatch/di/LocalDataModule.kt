package com.anugrah.majorsmatch.di

import com.anugrah.majorsmatch.data.repository.LocalDataSourceImpl
import com.anugrah.majorsmatch.domain.repository.ILocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataModule {

  @Binds
  @Singleton
  abstract fun bindLocalDataSource(
    localDataSourceImpl: LocalDataSourceImpl
  ): ILocalDataSource
}
