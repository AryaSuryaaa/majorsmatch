package com.anugrah.majorsmatch.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<in Param, Result> {
  operator fun invoke(param: Param): Flow<ResultState<Result>> = flow {
    emit(ResultState.Loading)
    execute(param).collect { data ->
      emit(ResultState.Success(data))
    }
  }.catch { e ->
    emit(ResultState.Error(e.localizedMessage ?: "Terjadi kesalahan, coba lagi!"))
  }

  protected abstract fun execute(param: Param): Flow<Result>
}
