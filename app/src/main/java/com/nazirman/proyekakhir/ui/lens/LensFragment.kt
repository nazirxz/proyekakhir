package com.nazirman.proyekakhir.ui.lens

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LensFragment : Fragment() {
    private lateinit var binding: FragmentLensBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var interpreter: Interpreter
    private val categories = listOf(
        "kupu-kupu", "kucing", "ayam", "burung", "gajah",
        "kelinci", "ikan", "kambing", "sapi", "jerapah"
    )
    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    private val CAMERA_IMAGE_REQUEST_CODE = 101
    private var imageCapture: ImageCapture? = null

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

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.btnUpload.setOnClickListener {
            checkPermissionAndCaptureImage()
        }

        loadModel()
        startCamera()
    }

    private fun loadModel() {
        val modelFile = "model.tflite" // Replace with your model file name
        try {
            interpreter = Interpreter(loadModelFile(modelFile))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadModelFile(modelFile: String): ByteBuffer {
        val assetManager = requireContext().assets
        val inputStream = assetManager.open(modelFile)
        val fileSize = inputStream.available()
        val modelBuffer = ByteBuffer.allocateDirect(fileSize)
        modelBuffer.order(ByteOrder.nativeOrder())
        val buffer = ByteArray(4096)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            modelBuffer.put(buffer, 0, bytesRead)
        }
        inputStream.close()
        return modelBuffer
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

        val outputOptions = ImageCapture.OutputFileOptions.Builder(getOutputFile()).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri
                    val bitmap = BitmapFactory.decodeFile(savedUri?.path)
                    processImage(bitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    exception.printStackTrace()
                }
            }
        )
    }

    private fun getOutputFile(): File {
        val mediaDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return File(mediaDir, "IMG_$timestamp.jpg")
    }

    private fun processImage(bitmap: Bitmap) {
        val inputImageBuffer = preprocessImage(bitmap)
        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, categories.size), DataType.FLOAT32)
        interpreter.run(inputImageBuffer.buffer, outputBuffer.buffer.rewind())

        val predictions = outputBuffer.floatArray
        val predictedClassIndex = predictions.indices.maxByOrNull { predictions[it] } ?: -1
        Log.d("Predicted Class Index",predictedClassIndex.toString())
        val predictedClass = if (predictedClassIndex != -1) categories[predictedClassIndex] else "Unknown"
        binding.tvResultRas.text = "$predictedClass"
    }

    private fun preprocessImage(bitmap: Bitmap): TensorImage {
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true)

        val inputImage = TensorImage(DataType.FLOAT32)
        inputImage.load(resizedBitmap)

        return inputImage
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.cameraView.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )

            } catch (exc: Exception) {
                exc.printStackTrace()
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
        interpreter.close()
    }
}