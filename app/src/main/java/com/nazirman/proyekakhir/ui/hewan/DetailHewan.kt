package com.nazirman.proyekakhir.ui.hewan

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.hewan.Hewan
import org.w3c.dom.Text

class DetailHewan(private val resultHewan: List<Hewan>) : RecyclerView.Adapter<DetailHewan.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_detail_hewan, parent, false)
        return DetailViewHolder(view)
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
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.nama_hewan)
        var tvDescription: TextView = itemView.findViewById(R.id.keteranganHewan)
        var ivPicture: ImageView = itemView.findViewById(R.id.gambar_hewan)
        var ivPicture2: ImageView = itemView.findViewById(R.id.habitat)
        var ivHabitat: TextView = itemView.findViewById(R.id.keteranganHabitat)
    }
}