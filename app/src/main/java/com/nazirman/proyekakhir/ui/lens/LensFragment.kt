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

        val outputOptions = ImageCapture.OutputFileOptions.Builder(createImageFile()).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri
                    val savedFile = File(savedUri?.path)
                    val bitmap = BitmapFactory.decodeFile(savedFile.absolutePath)
                    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true)
                    val result = recognizeImage(resizedBitmap)
                    showResult(result)
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

    private fun recognizeImage(bitmap: Bitmap): String {
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true)
        val byteBuffer = convertBitmapToByteBuffer(resizedBitmap)
        val modelOutput = Array(1) { FloatArray(categories.size) }
        interpreter.run(byteBuffer, modelOutput)
        val result = modelOutput[0]
        Log.d("Result",result.toString())
        val maxIndex = result.indices.maxByOrNull { result[it] } ?: -1
        Log.d("Max Index",maxIndex.toString())
        return categories[maxIndex]
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(80 * 80 * 3 * 4)
        byteBuffer.order(ByteOrder.nativeOrder())
        val pixels = IntArray(80 * 80)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var pixel = 0
        for (i in 0 until 80) {
            for (j in 0 until 80) {
                val pixelValue = pixels[pixel++]
                byteBuffer.putFloat(((pixelValue shr 16 and 0xFF) - 127) / 255.0f)
                byteBuffer.putFloat(((pixelValue shr 8 and 0xFF) - 127) / 255.0f)
                byteBuffer.putFloat(((pixelValue and 0xFF) - 127) / 255.0f)
            }
        }
        return byteBuffer
    }

    private fun showResult(result: String) {
        binding.tvResultRas.text = result
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