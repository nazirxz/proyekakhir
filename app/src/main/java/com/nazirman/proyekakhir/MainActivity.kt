package com.nazirman.proyekakhir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazirman.proyekakhir.data.hewan.Hewan
import com.nazirman.proyekakhir.data.hewan.HewanDao
import com.nazirman.proyekakhir.databinding.ActivityMainBinding
import com.nazirman.proyekakhir.ui.hewan.DetailHewanFragment
import com.nazirman.proyekakhir.ui.hewan.HewanAdapter

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<Hewan> = arrayListOf()
    private var listDetail: ArrayList<Hewan> = arrayListOf()

    private lateinit var binding: ActivityMainBinding
    private lateinit var HewanDao: HewanDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcList.visibility = View.VISIBLE
        binding.rcList.setHasFixedSize(true)

        showRecyclerList()
    }

    private fun showSelectedGuide(guideSelected: Hewan) {
        when (guideSelected.namaHewan) {
            "" -> {
                showDetailRecyclerList()

            }
            "" -> {
                showDetailRecyclerList()

            }
        }
    }

    private fun showRecyclerList() {
        binding.rcList.layoutManager = LinearLayoutManager(this)
        val HewanAdapter = HewanAdapter(list) // Pass the HewanDao to the adapter
        binding.rcList.adapter = HewanAdapter

        HewanAdapter.setOnItemClickCallback(object : HewanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hewan) {
                showSelectedGuide(data)
            }
        })
    }

    private fun showDetailRecyclerList() {
        binding.rcList.layoutManager = LinearLayoutManager(this)
        val listHewanDetail = DetailHewanFragment(listDetail)
        binding.rcList.adapter = listHewanDetail
    }
}