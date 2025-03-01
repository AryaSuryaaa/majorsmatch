package com.anugrah.majorsmatch.di

import android.content.Context
import com.anugrah.majorsmatch.data.repository.OnBoardingOperationImpl
import com.anugrah.majorsmatch.data.repository.Repository
import com.anugrah.majorsmatch.domain.UseCases
import com.anugrah.majorsmatch.domain.repository.OnBoardingOperations
import com.anugrah.majorsmatch.domain.usecase.readonboarding.ReadOnBoardingUseCase
import com.anugrah.majorsmatch.domain.usecase.saveonboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideDataStoreOperation(
    @ApplicationContext context: Context
  ): OnBoardingOperations = OnBoardingOperationImpl(context = context)

  @Provides
  @Singleton
  fun provideUseCases(repository: Repository): UseCases {
    return UseCases(
      saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
      readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
    )
  }
}