package com.nazirman.proyekakhir.ui.lens

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.databinding.FragmentLensBinding
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
class LensFragment : Fragment() {

    private lateinit var binding: FragmentLensBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var interpreter: Interpreter
    private val categories = listOf(
        "Kupu-kupu", "Kucing", "Ayam", "Burung", "Gajah",
        "Kelinci", "Ikan", "Kambing", "Sapi", "Jerapah"
    )
    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    private var imageCapture: ImageCapture? = null
    private val animalsWithSound = listOf(
        "Kucing", "Ayam", "Burung", "Gajah",
        "Kelinci", "Kambing", "Sapi", "Jerapah"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLensBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvPredicted.visibility = View.GONE
        cameraExecutor = Executors.newSingleThreadExecutor()
        binding.tvResultRas.visibility = View.GONE
        binding.btnSuara.visibility = View.GONE
        binding.btnUpload.setOnClickListener {
            checkPermissionAndCaptureImage()
        }
        loadModel()
        startCamera()
    }

    private fun loadModel() {
        try {
            val modelFile = "model.tflite" // Replace with your model file name
            val modelInputStream = requireContext().assets.open(modelFile)
            val modelBytes = modelInputStream.readBytes()
            val buffer = ByteBuffer.allocateDirect(modelBytes.size)
            buffer.order(ByteOrder.nativeOrder())
            buffer.put(modelBytes)
            buffer.rewind()
            interpreter = Interpreter(buffer as MappedByteBuffer)
        } catch (e: Exception) {
            Log.e("LensFragment", "Error loading model: ${e.message}", e)
        }
    }

    private fun checkPermissionAndCaptureImage() {
        val permission = Manifest.permission.CAMERA
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            captureImage()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(permission),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun captureImage() {
        val imageCapture = imageCapture ?: return

        val outputOptions = ImageCapture.OutputFileOptions.Builder(createImageFile()).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri
                    val savedFile = File(savedUri?.path)
                    val bitmap = BitmapFactory.decodeFile(savedFile?.absolutePath)
                    val (result, confidence) = recognizeImage(bitmap)
                    showResult(result, confidence)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("CameraXApp", "Photo capture failed: ${exception.message}", exception)
                }
            })
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    private fun recognizeImage(bitmap: Bitmap): Pair<String, Float> {
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true)
        val byteBuffer = convertBitmapToByteBuffer(resizedBitmap)
        val modelOutput = Array(1) { FloatArray(categories.size) }
        interpreter.run(byteBuffer, modelOutput)
        val result = modelOutput[0]
        val maxIndex = result.indices.maxByOrNull { result[it] } ?: -1
        val confidence = result[maxIndex]
        return Pair(categories[maxIndex], confidence)
    }
    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val inputSize = 80 // Modify this if your model requires a different input size
        val channelSize = 3 // RGB channels

        val byteBuffer = ByteBuffer.allocateDirect(inputSize * inputSize * channelSize * 4)
        byteBuffer.order(ByteOrder.nativeOrder())
        val pixels = IntArray(inputSize * inputSize)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var pixel = 0
        for (i in 0 until inputSize) {
            for (j in 0 until inputSize) {
                val pixelValue = pixels[pixel++]
                // Extract the RGB values from the pixel
                val r = (pixelValue shr 16 and 0xFF).toFloat()
                val g = (pixelValue shr 8 and 0xFF).toFloat()
                val b = (pixelValue and 0xFF).toFloat()
                // Normalize the RGB values to range [0, 1]
                byteBuffer.putFloat(r / 255.0f)
                byteBuffer.putFloat(g / 255.0f)
                byteBuffer.putFloat(b / 255.0f)
            }
        }
        return byteBuffer
    }

    private fun showResult(result: String, percentage: Float) {
        binding.tvResultRas.visibility = View.VISIBLE
        binding.tvResultRas.text = result

        // Display the predicted percentage in the tv_predicted TextView
        val limitedPercentage = if (percentage > 1.0f) 1.0f else percentage
        binding.tvPredicted.visibility = View.VISIBLE
        binding.tvPredicted.text = "${(limitedPercentage * 100).toInt()}%"


        // Check if the result matches any animal categories
        val matchedCategory = categories.indexOf(result)

        // Show/hide the "btn_suara" and set its click listener
        if (matchedCategory != -1) {
            if (animalsWithSound.contains(result)) {
                binding.btnSuara.visibility = View.VISIBLE
                binding.btnSuara.setOnClickListener {
                    // Handle the button click here
                    // For example, you can play the corresponding sound using MediaPlayer
                    val soundResId = when (matchedCategory) {
                        1 -> R.raw.kucing_sound
                        2 -> R.raw.ayam_sound
                        3 -> R.raw.burung_sound
                        4 -> R.raw.gajah_sound
                        5 -> R.raw.kelinci_sound
                        7 -> R.raw.kambing_sound
                        8 -> R.raw.sapi_sound
                        9 -> R.raw.jerapah_sound
                        else -> 0 // Replace with default sound resource or handle unknown cases
                    }
                    if (soundResId != 0) {
                        playSound(soundResId)
                    }
                }
            } else {
                binding.btnSuara.visibility = View.GONE
            }
        } else {
            binding.btnSuara.visibility = View.GONE
        }
    }

    private fun playSound(soundResId: Int) {
        val mediaPlayer = MediaPlayer.create(requireContext(), soundResId)
        mediaPlayer.start()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val previewImage = binding.cameraView
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewImage.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                Log.e("CameraXApp", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}