package ec.com.pablorcruh.receiptiq.presentation

import ec.com.pablorcruh.receiptiq.domain.model.CapturedReceipt

data class ReceiptScannerUIState(
    val capturedReceipt: CapturedReceipt? = null,
    val isCapturing: Boolean = false,
    val error: String ? = null
)
