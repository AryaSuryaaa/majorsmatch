package com.anugrah.majorsmatch.domain.usecase.settingsusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<Unit, String>() {
  override fun execute(param: Unit): Flow<String> {
    return repository.getTheme()
  }
}