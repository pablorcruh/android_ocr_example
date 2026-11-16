package ec.com.pablorcruh.receiptiq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ec.com.pablorcruh.receiptiq.presentation.camera.CameraController
import ec.com.pablorcruh.receiptiq.presentation.ReceiptScannerScreen
import ec.com.pablorcruh.receiptiq.presentation.ReceiptScannerViewModel
import ec.com.pablorcruh.receiptiq.ui.theme.ReceiptiqTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReceiptiqTheme {
                    val cameraController = koinInject<CameraController>()
                    val scannerViewModel = koinViewModel<ReceiptScannerViewModel>()
                    ReceiptScannerScreen(
                        uiState = scannerViewModel.uiState.collectAsStateWithLifecycle().value,
                        cameraController = cameraController,
                        onCapture = scannerViewModel::captureReceipts,
                        onRetake = scannerViewModel::retake
                    )
            }
        }
    }
}