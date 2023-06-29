package com.nazirman.proyekakhir.ui.hewan

import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.hewan.Hewan

class dummyHewan  {
    private val namaHewan = arrayOf("Ayam","Burung")
    private val deskripsiAyam = " Ayam adalah hewan unggas yang memiliki bulu berwarna-warni. Mereka suka berjalan dengan langkah-ngetap yang lucu dan bisa bertelur untuk membuat anak ayam baru."
    private val habitatAyam = "Ayam biasanya tinggal di kandang atau peternakan."


    private val habitatBurung = "Burung dapat ditemukan di berbagai tempat seperti hutan, taman, dan di dekat rumah kita."
    private val deskripsiBurung = "Burung adalah hewan yang bisa terbang. Mereka memiliki sayap yang kuat dan bisa membuat sarang untuk tempat tinggal mereka. Setiap burung memiliki warna dan suara yang berbeda-beda, dan mereka sering bernyanyi di pagi hari."

    private val deskripsiHewan = arrayOf(
        deskripsiAyam,
        deskripsiBurung)

    private val habitatHewan = arrayOf(
        habitatAyam,
        habitatBurung
    )

    private val gambarHewan = arrayOf<Int>(R.drawable.hayam,
        R.drawable.hburung)

    val listData: ArrayList<Hewan>
        get() {
            val list = arrayListOf<Hewan>()
            for (position in namaHewan.indices) {
                val name = Hewan(0,"","","",0)
                name.namaHewan= namaHewan[position]
                name.deskripsi = deskripsiHewan[position]
                name.gambar = gambarHewan[position]
                list.add(name)
            }
            return list
        }
}
