package com.nazirman.proyekakhir.ui.lens

import TFLiteHelper
import android.Manifest
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.databinding.FragmentLensBinding

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class LensFragment : Fragment() {
    private lateinit var binding: FragmentLensBinding
//    private lateinit var cameraExecutor: ExecutorService
//    private val CAMERA_PERMISSION_REQUEST_CODE = 100
//    private lateinit var interpreter: Interpreter
//    private lateinit var tfliteHelper: TFLiteHelper
//
//    private val categories = arrayOf("kupu-kupu", "kucing", "ayam", "burung", "gajah", "kelinci", "ikan", "kambing", "sapi", "jerapah") // Update with your categories
//    private val numClasses = categories.size

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
        setupToolbar()
//        requestCameraPermission() // Request camera permission before setting up the camera
//
////        // Load the TensorFlow Lite model
////        interpreter = Interpreter(loadModelFile())
//
//        buttonHandler()
//        // Initialize TFLiteHelper
//        tfliteHelper = TFLiteHelper(requireContext())
//        tfliteHelper.initialize()
    }

    private fun setupToolbar() {
//        val actionBar: androidx.appcompat.app.ActionBar? =
//            (activity as AppCompatActivity).supportActionBar
//        actionBar?.apply {
//            setTitle(R.string.title_lens)
//            setDisplayHomeAsUpEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
//        }
//        setHasOptionsMenu(true)
    }

//    private fun requestCameraPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (requireActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(
//                    arrayOf(Manifest.permission.CAMERA),
//                    CAMERA_PERMISSION_REQUEST_CODE
//                )
//            } else {
//                // Permission already granted
//                setupCamera()
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Camera permission granted
//                setupCamera()
//            } else {
//                // Camera permission denied
//                // Handle the case where the user denied the permission
//            }
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        }
//    }
//
//    private fun setupCamera() {
//        // Initialize cameraExecutor
//        cameraExecutor = Executors.newSingleThreadExecutor()
//
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
//
//        cameraProviderFuture.addListener({
//            // Used to bind the lifecycle of cameras to the lifecycle owner
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//
//            // Preview
//            val previewView = binding.cameraView
//
//            val preview = Preview.Builder().build().also {
//                it.setSurfaceProvider(previewView.surfaceProvider)
//            }
//
//            // Select back camera as a default
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                // Unbind use cases before rebinding
//                cameraProvider.unbindAll()
//
//                // Bind use cases to camera
//                val camera = cameraProvider.bindToLifecycle(viewLifecycleOwner, cameraSelector, preview)
//
//                // Log available camera devices
//                val cameraInfo = camera.cameraInfo
//                Log.d(TAG, "Camera Info: $cameraInfo")
//
//            } catch (exc: Exception) {
//                Log.e(TAG, "Use case binding failed", exc)
//            }
//        }, ContextCompat.getMainExecutor(requireContext()))
//    }

    override fun onDestroyView() {
        super.onDestroyView()
//        cameraExecutor.shutdown()
//        tfliteHelper.close()
    }
//
//    private fun buttonHandler() {
//        binding.btnBack.setOnClickListener {
//            requireActivity().finish()
//        }
//
//        binding.btnUpload.setOnClickListener {
//            // Get the current frame from the camera view
//            val bitmap = binding.cameraView.bitmap
//            if (bitmap != null) {
//                tfliteHelper.classifyAsync(bitmap)
//                    .addOnSuccessListener { result ->
//                        // Display the prediction result
//                        Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { exception ->
//                        // Handle classification failure
//                        Toast.makeText(requireContext(), "Classification failed: ${exception.message}", Toast.LENGTH_SHORT).show()
//                    }
//            }
//            if (bitmap != null) {
//                // Preprocess the image
//                val resizedImage = Bitmap.createScaledBitmap(bitmap, 80, 80, true)
//                val normalizedImage = normalizeImage(resizedImage)
//
//                // Reshape the image to match the model's input shape
//                val inputImage = normalizedImage.reshape(1, 80, 80, 3)
//
//                // Run inference on the image using your model
//                val prediction = runInference(inputImage)
//
//                // Get the predicted class index
//                val predictedClassIndex = prediction.indexOfFirst { it == prediction.maxOrNull() }
//
//                // Get the predicted class label
//                val predictedClass = categories[predictedClassIndex]
//
//                // Display the predicted class to the user
//                binding.tvResultRas.text = predictedClass
//            } else {
//                // Handle the case when the bitmap is null
//                Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
//            }
        }
    

//    private fun normalizeImage(bitmap: Bitmap): FloatArray {
//        val pixels = IntArray(bitmap.width * bitmap.height)
//        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
//
//        val normalizedImage = FloatArray(bitmap.width * bitmap.height * 3)
//        for (i in pixels.indices) {
//            val pixel = pixels[i]
//            normalizedImage[i * 3] = ((pixel shr 16) and 0xFF) / 255.0f // Red channel
//            normalizedImage[i * 3 + 1] = ((pixel shr 8) and 0xFF) / 255.0f // Green channel
//            normalizedImage[i * 3 + 2] = (pixel and 0xFF) / 255.0f // Blue channel
//        }
//
//        return normalizedImage
//    }
//
//    private fun runInference(inputImage: Array<FloatArray>): FloatArray {
//        val output = Array(1) { FloatArray(numClasses) }
//        interpreter.run(inputImage, output)
//        return output[0]
//    }
//
//    private fun loadModelFile(): ByteBuffer {
//        val assetManager: AssetManager = requireContext().assets
//        val fileDescriptor = assetManager.openFd("model.tflite")
//        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
//        val fileChannel = inputStream.channel
//        val startOffset = fileDescriptor.startOffset
//        val declaredLength = fileDescriptor.declaredLength
//        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
//    }
//
//    private fun Array<FloatArray>.flatten(): FloatArray {
//        val size = sumOf { it.size }
//        val result = FloatArray(size)
//        var offset = 0
//
//        for (array in this) {
//            array.copyInto(result, offset)
//            offset += array.size
//        }
//
//        return result
//    }
//
//    private fun FloatArray.reshape(vararg shape: Int): Array<FloatArray> {
//        require(size == shape.reduce(Int::times)) { "Array size does not match the shape" }
//
//        val result = Array(shape[0]) { FloatArray(shape[1]) }
//        var offset = 0
//
//        for (i in result.indices) {
//            for (j in result[i].indices) {
//                result[i][j] = this[offset++]
//            }
//        }
//
//        return result
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            requireActivity().finish()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    companion object {
//        private const val TAG = "LensFragment"
//    }


