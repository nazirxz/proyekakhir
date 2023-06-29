package com.nazirman.proyekakhir.ui.hewan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.hewan.Hewan

class DetailHewanFragment  (private val resultGuide: List<Hewan>) : RecyclerView.Adapter<DetailHewanFragment.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_hewan_list, parent,false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
//        val itemGuide = resultGuide[position]
//        Glide.with(holder.itemView.context)
//            .load(itemGuide.image)
//            .apply(RequestOptions().override(200,200))
//            .into(holder.ivPicture)
//        holder.tvName.text = itemGuide.guideName
//        holder.tvDescription.text = itemGuide.description
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
//        var ivPicture: ImageView = itemView.findViewById(R.id.iv_item_picture)
    }

}
