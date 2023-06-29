package com.nazirman.proyekakhir.ui.hewan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.hewan.Hewan

class DetailHewan (private val resultHewan: List<Hewan>) : Adapter<DetailHewan.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_detail_hewan, parent,false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val itemGuide = resultHewan[position]
        Glide.with(holder.itemView.context)
            .load(itemGuide.gambar)
            .apply(RequestOptions().override(200,200))
            .into(holder.ivPicture)
        holder.tvName.text = itemGuide.namaHewan
        holder.tvDescription.text = itemGuide.deskripsi
    }

    inner class DetailViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.nama_hewan)
        var tvDescription: TextView = itemView.findViewById(R.id.keteranganHewan)
        var ivPicture: ImageView = itemView.findViewById(R.id.gambar_hewan)
    }

}
