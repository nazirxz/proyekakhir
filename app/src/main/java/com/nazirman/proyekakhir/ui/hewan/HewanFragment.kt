package com.nazirman.proyekakhir.ui.hewan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazirman.proyekakhir.data.AnimalApllication
import com.nazirman.proyekakhir.data.hewan.Hewan
import com.nazirman.proyekakhir.databinding.FragmentHewanBinding

class HewanFragment : Fragment() {
    private var _binding: FragmentHewanBinding? = null
    private val binding get() = _binding as FragmentHewanBinding

    private var list: ArrayList<Hewan> = arrayListOf()
    private var listDetail: ArrayList<Hewan> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHewanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcHewan.visibility = View.VISIBLE
        binding.rcHewan.setHasFixedSize(true)

        list.addAll(dummyHewan.listData)
        showRecyclerList()
    }

    private fun showSelectedGuide(guideSelected: Hewan) {
        when (guideSelected.namaHewan) {
            "Ayam" -> {
                showDetailRecyclerList()
                listDetail.add(dummyHewan.listData[0])
            }
            "Burung" -> {
                showDetailRecyclerList()
                listDetail.add(dummyHewan.listData[1])
            }
        }
    }
    private fun showRecyclerList() {
        binding.rcHewan.layoutManager = LinearLayoutManager(requireContext())
        val hewanAdapter = HewanAdapter(list)
        binding.rcHewan.adapter = hewanAdapter

        hewanAdapter.setOnItemClickCallback(object : HewanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hewan) {
                showSelectedGuide(data)
            }
        })

    }


    private fun showDetailRecyclerList() {
        binding.rcHewan.layoutManager = LinearLayoutManager(requireContext())
        val listHewanDetail = DetailHewan(listDetail)
        binding.rcHewan.adapter = listHewanDetail
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
