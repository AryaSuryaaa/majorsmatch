package com.anugrah.majorsmatch.di

import com.anugrah.majorsmatch.data.repository.RemoteDataSourceImpl
import com.anugrah.majorsmatch.domain.repository.IRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataModule {

  @Binds
  @Singleton
  abstract fun bindRemoteDataSource(
    remoteDataSourceImpl: RemoteDataSourceImpl
  ): IRemoteDataSource
}
