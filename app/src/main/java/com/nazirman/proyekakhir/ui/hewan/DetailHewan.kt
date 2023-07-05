package com.nazirman.proyekakhir.ui.hewan

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.hewan.Hewan
import java.util.*

class DetailHewan(private val resultHewan: List<Hewan>) :
    RecyclerView.Adapter<DetailHewan.DetailViewHolder>(), TextToSpeech.OnInitListener {
    private var currentPos: Int = -1
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detail_hewan, parent, false)
        initializeTextToSpeech(parent.context)
        return DetailViewHolder(view)
    }

    private fun initializeTextToSpeech(context: Context) {
        textToSpeech = TextToSpeech(context, this)
    }
    override fun getItemCount(): Int {
        return resultHewan.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val itemGuide = resultHewan[position]
        val context = holder.itemView.context

        Glide.with(context)
            .load(itemGuide.gambar)
            .apply(RequestOptions().override(200, 200))
            .into(holder.ivPicture)

        Glide.with(context)
            .load(itemGuide.gambarHabitat)
            .apply(RequestOptions().override(200, 200))
            .into(holder.ivPicture2)

        holder.tvName.text = itemGuide.namaHewan
        holder.tvDescription.text = itemGuide.deskripsi
        holder.ivHabitat.text = itemGuide.keteranganHabitat

        holder.addFab.setOnClickListener {
            val text = "${itemGuide.namaHewan}. ${itemGuide.deskripsi}. Habitat. ${itemGuide.keteranganHabitat}."
            speakOut(text)
        }
    }

    private fun speakOut(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val language = Locale("id", "ID")
            val result = textToSpeech.setLanguage(language)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            } else {
                if (currentPos != -1) {
                    val text = resultHewan[currentPos].namaHewan
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        } else {
            Log.e("TTS", "Initialization failed!")
        }
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.nama_hewan)
        var tvDescription: TextView = itemView.findViewById(R.id.keteranganHewan)
        var ivPicture: ImageView = itemView.findViewById(R.id.gambar_hewan)
        var ivPicture2: ImageView = itemView.findViewById(R.id.habitat)
        var ivHabitat: TextView = itemView.findViewById(R.id.keteranganHabitat)
        var addFab: FloatingActionButton = itemView.findViewById(R.id.add_fab)
    }
}