import android.app.ActionBar
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.databinding.FragmentLensBinding
import com.nazirman.proyekakhir.reduceFileImage
import com.nazirman.proyekakhir.rotateBitmap
import com.nazirman.proyekakhir.ui.lens.LensViewModel
import com.nazirman.proyekakhir.ui.lens.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class LensActivity: Fragment() {
    private var _binding: FragmentLensBinding? = null
    private val binding get() = _binding!!
    private lateinit var lensViewModel: LensViewModel
    private lateinit var file: Bitmap
    private var getFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLensBinding.inflate(inflater, container, false)
        val view = binding.root
        setupToolbar()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        showLoading(false)
        showResultView(false)
        setupCamera()
        buttonHandler()
    }


    private fun setupToolbar() {
        val actionBar: androidx.appcompat.app.ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.apply {
            setTitle(R.string.title_lens)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
        }
        setHasOptionsMenu(true)
    }

    private fun buttonHandler() {
        binding.btnBack.setOnClickListener {
            requireActivity().finish()
        }

        binding.btnUpload.setOnClickListener {
            showLoading(true)
            uploadFileImage()
            getResult()
        }
    }

    private fun uploadFileImage() {
        val file = reduceFileImage(getFile as File)
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file", file.name, requestImageFile
        )
        // lensViewModel.getPredictPet(imageMultipart)
    }

    private fun getResult() {
        // lensViewModel.dataPredict.observe(this) {
        //     if (it != null) {
        //         showResultView(true)
        //         if (it.error == null) {
        //             setDataResult(it.breed, it.percentage)
        //         }
        //     }
        // }
    }

    private fun setDataResult(breed: String?, percentage: String?) {
        binding.tvResultRas.text = breed
    }

    private fun setupCamera() {
        val myFile = requireArguments().getSerializable("picture") as File
        val isBackCamera = requireArguments().getBoolean("isBackCamera", true)
        val result = rotateBitmap(
            BitmapFactory.decodeFile(myFile.path),
            isBackCamera
        )
        getFile = myFile

        // binding.previewImage.load(result) {
        //     crossfade(true)
        //     crossfade(1000)
        // }
    }

    private fun setupViewModel() {
        lensViewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        ).get(LensViewModel::class.java)
    }

    private fun resultHandler() {
        // TODO: result FROM ML
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
        if (b) {
            binding.layoutResult.visibility = View.VISIBLE
            binding.btnUpload.visibility = View.GONE
            showLoading(false)
        } else {
            binding.layoutResult.visibility = View.GONE
            showLoading(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        requireActivity().actionBar?.hide()
        showLoading(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            0
        )
        _binding = null
    }
}
