package com.anugrah.majorsmatch.domain.usecase.settingsusecase

import com.anugrah.majorsmatch.domain.repository.IRepository
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(
  private val repository: IRepository
) {
  suspend operator fun invoke(language: String) = repository.saveLanguage(language)
}