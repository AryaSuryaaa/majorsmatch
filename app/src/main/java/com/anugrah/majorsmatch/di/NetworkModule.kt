package com.anugrah.majorsmatch.di

import com.anugrah.majorsmatch.BuildConfig.BASE_URL
import com.anugrah.majorsmatch.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideAuthInterceptor(
  ): Interceptor {
    return Interceptor { chain ->
      val originalRequest = chain.request()
      val originalHttpUrl = originalRequest.url

      val newHttpUrl = originalHttpUrl.newBuilder()
        .build()

      val newRequest = originalRequest.newBuilder()
        .url(newHttpUrl)
        .build()

      chain.proceed(newRequest)
    }
  }


  // response on logcat
  @Provides
  @Singleton
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY  // FULL LOGGING
    }
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(
    authInterceptor: Interceptor,
    loggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(authInterceptor)  // Interceptor untuk API Key
      .addInterceptor(loggingInterceptor)  // Logging Interceptor
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }
}