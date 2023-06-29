package com.nazirman.proyekakhir.ui.hewan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazirman.proyekakhir.data.hewan.Hewan
import com.nazirman.proyekakhir.databinding.ItemHewanListBinding
import kotlinx.android.synthetic.main.activity_splashscreen.view.*

class HewanAdapter(private val resultAnimal: List<Hewan>) : RecyclerView.Adapter<HewanAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemHewanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultAnimal.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemGuide = resultAnimal[position]
        val context = holder.itemView.context

        Glide.with(context)
            .load(itemGuide.gambar)
            .apply(RequestOptions().override(60, 60))
            .into(holder.binding.gambarHewan)

        holder.binding.namaHewanTextView.text = itemGuide.namaHewan
        holder.binding.keteranganTextView.text = itemGuide.deskripsi

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(resultAnimal[holder.adapterPosition])
        }
    }

    inner class ListViewHolder(val binding: ItemHewanListBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Hewan)
    }
}