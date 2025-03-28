package com.anugrah.majorsmatch.ui.screen.survey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.domain.usecase.surveyusecase.GetQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
  private val getQuestionUseCase: GetQuestionUseCase
): ViewModel() {
  private val _uiState = MutableStateFlow(SurveyUiState())
  val uiState get() = _uiState

  fun getQuestion() {
    viewModelScope.launch {
      getQuestionUseCase(Unit).collect{ result ->
        _uiState.update { state ->
          when(result) {
            is ResultState.Success -> {
              state.copy(questions = result.data.dataQuestions)
            }
            else -> state
          }
        }
      }
    }
  }

  fun updateAnswer(questionId: Int, answer: Boolean) {
    _uiState.update { state ->
      val updatedAnswers = state.answers.toMutableMap()
      updatedAnswers[questionId] = answer
      state.copy(answers = updatedAnswers)
    }
  }
}