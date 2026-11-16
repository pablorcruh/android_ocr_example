package ec.com.pablorcruh.receiptiq.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.com.pablorcruh.receiptiq.presentation.camera.CameraController
import ec.com.pablorcruh.receiptiq.domain.model.CapturedReceipt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class ReceiptScannerViewModel(
    val cameraController: CameraController
): ViewModel(){
    private val _uiState = MutableStateFlow<ReceiptScannerUIState>(ReceiptScannerUIState())
    val uiState: StateFlow<ReceiptScannerUIState> = _uiState.asStateFlow()

    fun captureReceipts(){
        if(_uiState.value.isCapturing) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isCapturing = true,
                error = null
            )
            runCatching{
                cameraController.captureReceipt()
            }
                .onSuccess {
                    _uiState.value = ReceiptScannerUIState(
                        capturedReceipt = CapturedReceipt(it)
                    )
                }
                .onFailure {
                    _uiState.value = ReceiptScannerUIState(
                        error = it.message
                    )
                }
        }
    }
    fun retake(){
        _uiState.value = ReceiptScannerUIState()
    }
}