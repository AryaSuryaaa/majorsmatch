package com.anugrah.majorsmatch.data.dummy

import com.anugrah.majorsmatch.domain.model.Fakultas
import com.anugrah.majorsmatch.domain.model.Universitas

val universitasList = listOf(
  Universitas(
    id = 1,
    nama = "Universitas Indonesia",
    imgLogo = "https://www.academicindonesia.com/wp-content/uploads/2016/09/Logo-UI.png",
    imgBanner = "https://awsimages.detik.net.id/community/media/visual/2019/02/12/43b5e043-b813-47c9-b0c1-3c8fa14bf6ae_169.jpeg?w=1200",
    deskripsi = "Universitas Teknologi Nusantara adalah institusi pendidikan tinggi yang berfokus pada inovasi dan teknologi untuk menciptakan lulusan berkualitas tinggi.",
    acronym = "UI",
    fakultas = listOf(
      Fakultas(
        nama = "Fakultas Teknik",
        jurusan = listOf("Teknik Informatika", "Teknik Elektro", "Teknik Mesin", "Teknik Sipil")
      ),
      Fakultas(
        nama = "Fakultas Ekonomi dan Bisnis",
        jurusan = listOf("Manajemen", "Akuntansi", "Ekonomi Pembangunan")
      )
    )
  ),
  Universitas(
    id = 2,
    nama = "Institut Teknologi Bandung",
    imgLogo = "https://palmoilina.asia/wp-content/uploads/2023/01/Institut-Teknologi-Bandung.webp",
    imgBanner = "https://atourin.obs.ap-southeast-3.myhuaweicloud.com/images/destination/bandung/institut-teknologi-bandung-profile1695282317.jpeg?x-image-process=image/resize,p_100,limit_1/imageslim",
    deskripsi = "Universitas Sains dan Humaniora menggabungkan ilmu sains dan sosial untuk menciptakan lulusan yang kompetitif dalam berbagai bidang.",
    acronym = "ITB",
    fakultas = listOf(
      Fakultas(
        nama = "Fakultas Sains dan Matematika",
        jurusan = listOf("Matematika", "Fisika", "Kimia", "Biologi")
      ),
      Fakultas(
        nama = "Fakultas Ilmu Sosial",
        jurusan = listOf("Ilmu Komunikasi", "Sosiologi", "Ilmu Politik")
      )
    ),
  ),
  Universitas(
    id = 3,
    nama = "Intitut Teknologi Sepuluh Nopember",
    imgLogo = "https://www.its.ac.id/wp-content/uploads/2020/07/Lambang-ITS-2-300x300.png",
    imgBanner = "https://awsimages.detik.net.id/community/media/visual/2021/06/11/kampus-its-surabaya.jpeg?w=600&q=90",
    deskripsi = "Universitas Digital Indonesia berkomitmen dalam pendidikan berbasis teknologi digital dan inovasi startup. Universitas Digital Indonesia berkomitmen dalam pendidikan berbasis teknologi digital dan inovasi startup.Universitas Digital Indonesia berkomitmen dalam pendidikan berbasis teknologi digital dan inovasi startup.",
    acronym = "ITS",
    fakultas = listOf(
      Fakultas(
        nama = "Fakultas Ilmu Komputer",
        jurusan = listOf("Sistem Informasi", "Teknik Informatika", "Rekayasa Perangkat Lunak")
      ),
      Fakultas(
        nama = "Fakultas Desain dan Kreatif",
        jurusan = listOf("Desain Komunikasi Visual", "Seni Rupa", "Multimedia")
      ),
      Fakultas(
        nama = "Fakultas Ilmu Komputer",
        jurusan = listOf("Sistem Informasi", "Teknik Informatika", "Rekayasa Perangkat Lunak")
      ),
      Fakultas(
        nama = "Fakultas Desain dan Kreatif",
        jurusan = listOf("Desain Komunikasi Visual", "Seni Rupa", "Multimedia")
      ),
      Fakultas(
        nama = "Fakultas Ilmu Komputer",
        jurusan = listOf("Sistem Informasi", "Teknik Informatika", "Rekayasa Perangkat Lunak")
      ),
      Fakultas(
        nama = "Fakultas Desain dan Kreatif",
        jurusan = listOf("Desain Komunikasi Visual", "Seni Rupa", "Multimedia")
      )
    )
  )
)