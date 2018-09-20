package com.telecomuc.bookqr.camera

import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.telecomuc.bookqr.R
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.layout_qr_code_reader.*

class CameraActivity : BaseCameraActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomSheet(R.layout.layout_qr_code_reader)
    }

    override fun onClick(v: View) {
        fabProgressCircle.show()
        cameraView.captureImage { cameraKitImage ->
            // Get the Bitmap from the captured shot
            getQRCodeDetails(cameraKitImage.bitmap)
            runOnUiThread {
                showPreview()
                imagePreview.setImageBitmap(cameraKitImage.bitmap)
            }
        }
    }

    private fun getQRCodeDetails(bitmap: Bitmap) {
        val options = FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(
                        FirebaseVisionBarcode.FORMAT_ALL_FORMATS)
                .build()
        val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        detector.detectInImage(image)
                .addOnSuccessListener {
                    for (firebaseBarcode in it) {

                        codeData.text = firebaseBarcode.displayValue //Display contents inside the barcode
                        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                        when (firebaseBarcode.valueType) {
                            // Handle the URL here
                            FirebaseVisionBarcode.TYPE_URL -> {
                                val url = firebaseBarcode.url

                            }

                            // Handle ID
                            FirebaseVisionBarcode.TYPE_TEXT -> {

                            }
                        }

                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    Toast.makeText(baseContext, "Sorry, something went wrong!", Toast.LENGTH_SHORT).show()
                }
                .addOnCompleteListener {
                    fabProgressCircle.hide()
                }
    }
}
