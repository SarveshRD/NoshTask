package presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.connectup.company.data.repository.ApiStatus
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.noshtask.data.model.response.JsonResponse
import org.example.noshtask.data.repository.CookRepository


class CookViewModel (private val cookRepository: CookRepository) : ViewModel() {


    private val _cookState = MutableStateFlow(CookState())
    private val _cookViewState = MutableStateFlow<CookScreenState>(
        CookScreenState.Loading
    )
    val cookViewState = _cookViewState.asStateFlow()

    suspend fun getData() {
        viewModelScope.launch {
            try {
                cookRepository.getData()
                    .collect { response ->
                        when (response.status) {
                            ApiStatus.LOADING -> {
                                _cookState.update { it.copy(isLoading = true) }
                            }

                            ApiStatus.SUCCESS -> {
                                _cookState.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = "",
                                        response.data
                                    )
                                }
                            }

                            ApiStatus.ERROR -> {
                                _cookState.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = response.message
                                    )
                                }
                            }
                        }
                        _cookViewState.value = _cookState.value.toUiState()
                    }
            } catch (e: Exception) {
                _cookState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch data"
                    )
                }
            }
        }
    }

    sealed class CookScreenState {
        object Loading : CookScreenState()
        class Error(val errorMessage: String) : CookScreenState()
        class Success(val responseData: JsonResponse) : CookScreenState()
    }

    private data class CookState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val responseData: JsonResponse? = null
    ) {
        fun toUiState(): CookScreenState {
            return if (isLoading) {
                CookScreenState.Loading
            } else if (errorMessage?.isNotEmpty() == true) {
                CookScreenState.Error(errorMessage)
            } else {
                CookScreenState.Success(responseData!!)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}