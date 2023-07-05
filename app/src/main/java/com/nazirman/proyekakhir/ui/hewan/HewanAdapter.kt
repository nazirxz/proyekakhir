package com.nazirman.proyekakhir.ui.hewan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.hewan.Hewan
import com.nazirman.proyekakhir.databinding.ItemHewanListBinding

class HewanAdapter(private val context: Context, private val resultAnimal: List<Hewan>) : RecyclerView.Adapter<HewanAdapter.ListViewHolder>() {
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
        val itemColor = ContextCompat.getColor(context, itemColors[position % itemColors.size])
        holder.itemView.setBackgroundColor(itemColor)
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
    private val itemColors = intArrayOf(
        R.color.item1, R.color.item2, R.color.item3, R.color.item4
        // Add more color resource IDs as needed
    )

        inner class ListViewHolder(val binding: ItemHewanListBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Hewan)
    }
}