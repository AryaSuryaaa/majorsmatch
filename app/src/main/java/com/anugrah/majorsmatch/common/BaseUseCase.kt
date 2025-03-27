package com.anugrah.majorsmatch.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

abstract class BaseUseCase<in Param, Result> {
  operator fun invoke(param: Param): Flow<ResultState<Result>> = flow {
    emit(ResultState.Loading)
    execute(param).collect { data ->
      emit(ResultState.Success(data))
    }
  }.catch { e ->
    val errorMessage = when (e) {
      is HttpException -> {
        val errorBody = e.response()?.errorBody()?.string()
        val apiCode = parseErrorCode(errorBody)
        val apiMessage = parseErrorMessage(errorBody)

        getCustomErrorMessage(apiCode ?: e.code(), apiMessage)
      }
      is IOException -> "Tidak ada koneksi internet. Periksa jaringan Anda!"
      else -> e.localizedMessage ?: "Terjadi kesalahan yang tidak diketahui."
    }
    emit(ResultState.Error(errorMessage))
  }

  protected abstract fun execute(param: Param): Flow<Result>

  private fun parseErrorMessage(errorBody: String?): String? {
    return try {
      errorBody?.let {
        val jsonObject = JSONObject(it)
        jsonObject.getString("message")
      }
    } catch (e: Exception) {
      null
    }
  }

  private fun parseErrorCode(errorBody: String?): Int? {
    return try {
      errorBody?.let {
        val jsonObject = JSONObject(it)
        jsonObject.getInt("code")
      }
    } catch (e: Exception) {
      null
    }
  }


  private fun getCustomErrorMessage(code: Int, defaultMessage: String?): String {
    return when (code) {
      400 -> "Permintaan tidak valid. Silakan cek kembali input Anda."
      401 -> "Email atau password salah."
      403 -> "Akses ditolak. Anda tidak memiliki izin."
      404 -> "Data tidak ditemukan."
      500 -> "Terjadi kesalahan pada server. Coba lagi nanti."
      else -> defaultMessage ?: "Terjadi kesalahan, coba lagi!"
    }
  }
}
