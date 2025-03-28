package com.anugrah.majorsmatch.domain.usecase.settingsusecase

import com.anugrah.majorsmatch.domain.repository.IRepository
import javax.inject.Inject

class SaveThemeUseCase @Inject constructor(
  private val repository: IRepository
) {
  suspend operator fun invoke(theme: String) {
    repository.saveTheme(theme = theme)
  }
}
