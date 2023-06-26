package com.nazirman.proyekakhir.ui.lens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.databinding.ActivityLensBinding
import com.nazirman.proyekakhir.reduceFileImage
import com.nazirman.proyekakhir.rotateBitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class LensActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLensBinding
    private lateinit var lensViewModel: LensViewModel
    private lateinit var file : Bitmap
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setTitle(R.string.title_lens)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
        }
        binding = ActivityLensBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        showLoading(false)
        showResultView(false)
        setupCamera()
        buttonHandler()
    }

    private fun buttonHandler() {
        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnUpload.setOnClickListener {
            showLoading(true)
            uploadFileImage()
            getResult()
        }
    }


    private fun uploadFileImage() {
        //files
        val file = reduceFileImage(getFile as File)
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",file.name,requestImageFile
        )
//        lensViewModel.getPredictPet(imageMultipart)
    }

    private fun getResult() {

//        lensViewModel.dataPredict.observe(this){
//
//            if(it != null) {
//                showResultView(true)
//                if(it.error == null){
//                    setDataResult(it.breed, it.percentage)
//                }
//            }
//
//        }
    }

    private fun setDataResult(breed: String?, percentage: String?) {
        binding.tvResultRas.text = breed
        binding.tvResultPresentase.text ="$percentage%"
    }


    private fun setupCamera() {
        val myFile = intent?.getSerializableExtra("picture") as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", true)
        val result = rotateBitmap(
            BitmapFactory.decodeFile(myFile.path),
            isBackCamera
        )
        getFile = myFile

//        binding.previewImage.load(result){
//            crossfade(true)
//            crossfade(1000)
//        }
    }

    private fun setupViewModel() {
        lensViewModel = ViewModelProvider(this,
            ViewModelFactory(this)
        )[LensViewModel::class.java]
    }

    private fun resultHandler() {
        //TODO: result FROM ML
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvProgress.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.tvProgress.visibility = View.GONE
        }
    }

    private fun showResultView(b: Boolean) {
        if(b){
            binding.layoutResult.visibility = View.VISIBLE
            binding.btnUpload.visibility = View.GONE
            showLoading(false)
        }else{
            binding.layoutResult.visibility = View.GONE
            showLoading(true)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onContextItemSelected(item)
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
        showLoading(false)
    }
}