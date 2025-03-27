package com.anugrah.majorsmatch.domain.usecase.homeusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<Unit, List<University>>() {
  override fun execute(param: Unit): Flow<List<University>> {
    return repository.getUniversities()
  }
}