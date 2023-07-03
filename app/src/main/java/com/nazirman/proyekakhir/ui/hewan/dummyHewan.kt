package com.nazirman.proyekakhir.ui.hewan

import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.hewan.Hewan


object dummyHewan {
    private val namaHewan = arrayOf("Ayam","Burung","Cicak","Domba","Elang","Flamingo","Gajah","Harimau","Ikan","Jerapah","Kambing","Kucing","Kelinci",
        "Kupu-Kupu","Sapi","Monyet","Panda","Rusa","Ular","Zebra")
    private val deskripsiAyam = "Ayam adalah hewan unggas yang memiliki bulu berwarna-warni. Mereka suka berjalan dengan langkah-ngetap yang lucu dan bisa bertelur untuk membuat anak ayam baru"
    private val habitatAyam = "Ayam biasanya tinggal di kandang atau peternakan"

    private val habitatBurung = "Burung dapat ditemukan di berbagai tempat seperti hutan, taman, dan di dekat rumah kita"
    private val deskripsiBurung = "Burung adalah hewan yang bisa terbang. Mereka memiliki sayap yang kuat dan bisa membuat sarang untuk tempat tinggal mereka. Setiap burung memiliki warna dan suara yang berbeda-beda, dan mereka sering bernyanyi di pagi hari"

    private val deskripsiCicak = "Cicak sendiri adalah hewan reptil yang biasa merayap di dinding atau pohon. Cicak berwarna abu-abu, tetapi ada pula yang berwarna coklat kehitam-hitaman. Cicak biasanya berukuran sekitar 10 centimeter"
    private val habitatCicak = "Cicak sering terlihat di sekitar rumah atau di pepohonan"

    private val deskripsiDomba = "Domba adalah hewan ternak yang memiliki bulu lembut dan tebal. Mereka suka menggembalakan dan makan rumput. Domba juga memberikan kita wol yang digunakan untuk membuat pakaian hangat seperti sweater dan syal"
    private val habitatDomba = "Domba tinggal di padang rumput atau peternakan"

    private val deskripsiElang = "Elang adalah burung yang memiliki sayap lebar dan cakar yang kuat. Mereka bisa terbang sangat tinggi di langit dan memiliki penglihatan yang tajam. Elang adalah simbol kekuatan dan kebebasan"
    private val habitatElang = "Elang sering ditemukan di pegunungan dan daerah terbuka yang tinggi"

    private val deskripsiFlamingo = "Flamingo adalah burung yang memiliki bulu berwarna merah muda atau oranye. Mereka memiliki leher yang panjang dan kaki yang tinggi. Flamingo suka mencari makanan di air, seperti ikan-ikan kecil, udang, dan kerang"
    private val habitatFlamingo = "Flamingo hidup di rawa-rawa dan danau dengan air yang dangkal"

    private val deskripsiGajah = "Gajah adalah hewan mamalia terbesar di darat. Mereka memiliki tubuh yang besar, telinga yang lebar, dan belalai yang panjang. Gajah sangat cerdas dan suka hidup dalam kelompok besar yang disebut kawanan"
    private val habitatGajah = "Gajah tinggal di hutan dan padang rumput di Afrika dan Asia"

    private val deskripsiHarimau = "Harimau adalah kucing terbesar di dunia. Mereka memiliki belang-beling di tubuh mereka yang membantu mereka bersembunyi di antara rumput tinggi. Harimau adalah pemburu yang tangguh dan bisa melompat jauh untuk menangkap mangsa"
    private val habitatHarimau = "Harimau hidup di hutan-hutan tropis dan hutan bambu di Asia."

    private val deskripsiIkan = "Ikan adalah hewan yang hidup di dalam air. Mereka memiliki sirip dan insang untuk bernapas di air. Ada banyak jenis ikan dengan warna, bentuk, dan ukuran yang berbeda. Beberapa ikan bisa berenang sangat cepat, sementara yang lain bisa bersembunyi di antara terumbu karang"
    private val habitatIkan = "Ikan hidup di air, seperti di sungai, danau, dan samudra"

    private val deskripsiJerapah = "Jerapah adalah hewan dengan leher yang sangat panjang dan memiliki kaki yang tinggi. Mereka bisa mencapai daun-daun pohon yang tinggi untuk makan. Jerapah memiliki lidah yang panjang dan bisa menggapai daun-daun di atas pohon"
    private val habitatJerapah = "Jerapah hidup di padang rumput dan hutan di Afrika"

    private val deskripsiKambing = "Kambing adalah hewan ternak yang memiliki tanduk kecil di kepalanya. Mereka suka makan rumput dan daun-daunan. Kambing juga memberikan kita susu yang bisa diolah menjadi keju dan yoghurt"
    private val habitatKambing = "Kambing tinggal di pegunungan, padang rumput, dan peternakan"

    private val deskripsiKucing = "Kucing adalah hewan peliharaan yang lucu dan lincah. Mereka memiliki bulu yang lembut dan mata yang tajam. Kucing suka bermain dengan bola atau tali, dan mereka bisa menjilati diri sendiri dengan lidah yang kasar"
    private val habitatKucing = "Kucing bisa hidup di dalam rumah atau di luar rumah"

    private val deskripsiKelinci = "Kelinci adalah hewan mamalia kecil yang memiliki telinga panjang dan kaki yang kuat. Mereka suka makan wortel dan rumput. Kelinci juga bisa melompat tinggi dan cepat"
    private val habitatKelinci = "Kelinci biasanya tinggal di padang rumput atau di bawah tanah"

    private val deskripsiKupu2 = "Kupu-kupu adalah serangga yang memiliki sayap berwarna-warni dan indah. Mereka mengubah diri dari ulat menjadi kepompong dan akhirnya menjadi kupu-kupu. Kupu-kupu suka menghisap nectar bunga dengan belalainya yang panjang"
    private val habitatKupu2 = "Kupu-kupu bisa ditemukan di berbagai tempat seperti taman dan kebun bunga"

    private val deskripsiSapi = "Sapi adalah hewan ternak yang memiliki tubuh besar dan tanduk di kepalanya. Mereka memberikan kita susu yang bisa diminum dan daging yang bisa dimakan. Sapi juga digunakan untuk membajak ladang atau mengangkut beban"
    private val habitatSapi = "Sapi biasanya tinggal di peternakan atau padang rumput"

    private val deskripsiMonyet = "Monyet adalah hewan primata yang lincah dan cerdas. Mereka memiliki ekor yang panjang dan bisa bergelantungan di pepohonan. Monyet suka makan buah-buahan dan kadang-kadang suka memainkan trik-trik lucu"
    private val habitatMonyet = "Monyet bisa ditemukan di hutan-hutan tropis di berbagai negara"

    private val deskripsiPanda = "Panda adalah hewan yang lucu dengan bulu hitam dan putih. Mereka suka makan bambu dan memiliki kebiasaan tidur yang lama. Panda adalah simbol keselamatan dan dilindungi karena mereka langka"
    private val habitatPanda = "Panda hidup di hutan bambu di Tiongkok"

    private val deskripsiRusa = "Rusa adalah hewan mamalia yang memiliki tanduk di kepala. Mereka suka makan rumput dan daun-daunan. Rusa jantan memiliki tanduk yang besar dan digunakan untuk bertarung dalam musim kawin"
    private val habitatRusa = "Rusa biasanya hidup di hutan-hutan dan padang rumput"

    private val deskripsiUlar = " Ular adalah reptil yang panjang dan licin. Mereka tidak memiliki kaki, tetapi bisa meluncur dengan lincah. Ada banyak jenis ular dengan warna, ukuran, dan pola yang berbeda. Beberapa ular bisa berbisa, sementara yang lain tidak berbahaya bagi manusia."
    private val habitatUlar = "Ular bisa ditemukan di berbagai habitat seperti hutan, padang rumput, dan lahan basah"

    private val deskripsiZebra = "Zebra adalah hewan yang memiliki tubuh berwarna hitam dan putih dengan garis-garis yang khas. Mereka memiliki kaki yang kuat dan bisa berlari dengan cepat. Zebra hidup dalam kawanan besar dan menggunakan garis-garis mereka untuk melindungi diri dari predator. Mereka suka makan rumput dan daun-daunan di padang rumput"
    private val habitatZebra = "Zebra biasanya ditemukan di padang rumput Afrika"

    private val deskripsiHewan = arrayOf(
        deskripsiAyam,
        deskripsiBurung,
        deskripsiCicak,
        deskripsiDomba,
        deskripsiElang,
        deskripsiFlamingo,
        deskripsiGajah,
        deskripsiHarimau,
        deskripsiIkan,
        deskripsiJerapah,
        deskripsiKambing,
        deskripsiKucing,
        deskripsiKelinci,
        deskripsiKupu2,
        deskripsiSapi,
        deskripsiMonyet,
        deskripsiPanda,
        deskripsiRusa,
        deskripsiUlar,
        deskripsiZebra)

    private val habitatHewan = arrayOf(
        habitatAyam,
        habitatBurung,
        habitatCicak,
        habitatDomba,
        habitatElang,
        habitatFlamingo,
        habitatGajah,
        habitatHarimau,
        habitatIkan,
        habitatJerapah,
        habitatKambing,
        habitatKucing,
        habitatKelinci,
        habitatKupu2,
        habitatSapi,
        habitatMonyet,
        habitatPanda,
        habitatRusa,
        habitatUlar,
        habitatZebra)

    private val gambarHewan = arrayOf<Int>(R.drawable.hayam,
        R.drawable.hburung,
        R.drawable.hcicak,
        R.drawable.hdomba,
        R.drawable.helang,
        R.drawable.hflamingo,
        R.drawable.hgajah,
        R.drawable.hharimau,
        R.drawable.hikan,
        R.drawable.hjerapah,
        R.drawable.hkambing,
        R.drawable.hkucing,
        R.drawable.hkelinci,
        R.drawable.hkupu2,
        R.drawable.hsapi,
        R.drawable.hmonyet,
        R.drawable.hpanda,
        R.drawable.hrusa,
        R.drawable.hular,
        R.drawable.hzebra
    )

    private val gambarHabitat = arrayOf<Int>(R.drawable.hayam,
        R.drawable.hburung,
        R.drawable.habcicak,
        R.drawable.habdomba,
        R.drawable.habelang,
        R.drawable.habflamingo,
        R.drawable.habgajah,
        R.drawable.habharimau,
        R.drawable.habikan,
        R.drawable.habjerapah,
        R.drawable.habkambing,
        R.drawable.habkambing,
        R.drawable.habkucing,
        R.drawable.habkupu2,
        R.drawable.habsapi,
        R.drawable.habmonyet,
        R.drawable.habpanda,
        R.drawable.habrusa,
        R.drawable.habular,
        R.drawable.habzebras)

    val listData: ArrayList<Hewan>
        get() {
            val list = arrayListOf<Hewan>()
            for (position in namaHewan.indices) {
                val name = Hewan(0,"","","",0,0)
                name.namaHewan= namaHewan[position]
                name.deskripsi = deskripsiHewan[position]
                name.keteranganHabitat = habitatHewan[position]
                name.gambar = gambarHewan[position]
                name.gambarHabitat = gambarHabitat[position]
                list.add(name)
            }
            return list
        }
}
