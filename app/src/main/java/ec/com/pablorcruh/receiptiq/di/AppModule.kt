package ec.com.pablorcruh.receiptiq.di

import android.content.Context
import ec.com.pablorcruh.receiptiq.presentation.camera.CameraController
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Module
@ComponentScan("ec.com.pablorcruh.receiptiq.presentation")
class AppModule {
    @Single
    fun cameraController(@Provided context: Context): CameraController {
        return CameraController(context)
    }

}