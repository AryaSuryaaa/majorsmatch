package com.anugrah.majorsmatch.domain.usecase.searchusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apiresponse.DataUniversity
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<String, List<DataUniversity>>(){
  override fun execute(param: String): Flow<List<DataUniversity>> {
    return repository.searchUniversity(param)
  }
}