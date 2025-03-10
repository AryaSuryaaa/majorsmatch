package com.anugrah.majorsmatch.data.dummy

import com.anugrah.majorsmatch.domain.model.Faculty
import com.anugrah.majorsmatch.domain.model.University

val universityLists = listOf(
  University(
    id = 1,
    name = "Universitas Indonesia",
    imgLogo = "https://www.pngkey.com/png/full/406-4061740_makara-ui-png-logo-universitas-indonesia.png",
    imgBanner = "https://awsimages.detik.net.id/community/media/visual/2019/02/12/43b5e043-b813-47c9-b0c1-3c8fa14bf6ae_169.jpeg?w=1200",
    description = "Universitas Teknologi Nusantara adalah institusi pendidikan tinggi yang berfokus pada inovasi dan teknologi untuk menciptakan lulusan berkualitas tinggi.",
    acronym = "UI",
    faculty = listOf(
      Faculty(
        name = "Fakultas Teknik",
        major = listOf("Teknik Informatika", "Teknik Elektro", "Teknik Mesin", "Teknik Sipil")
      ),
      Faculty(
        name = "Fakultas Ekonomi dan Bisnis",
        major = listOf("Manajemen", "Akuntansi", "Ekonomi Pembangunan")
      )
    ),
    website = "https://www.ui.ac.id/"
  ),
  University(
    id = 2,
    name = "Institut Teknologi Bandung",
    imgLogo = "https://palmoilina.asia/wp-content/uploads/2023/01/Institut-Teknologi-Bandung.webp",
    imgBanner = "https://itb.ac.id/files/cover/170125-Kolam-Intel.jpg",
    description = "Universitas Sains dan Humaniora menggabungkan ilmu sains dan sosial untuk menciptakan lulusan yang kompetitif dalam berbagai bidang.",
    acronym = "ITB",
    faculty = listOf(
      Faculty(
        name = "Fakultas Sains dan Matematika",
        major = listOf("Matematika", "Fisika", "Kimia", "Biologi")
      ),
      Faculty(
        name = "Fakultas Ilmu Sosial",
        major = listOf("Ilmu Komunikasi", "Sosiologi", "Ilmu Politik")
      )
    ),
    website = "https://www.itb.ac.id/"
  ),
  University(
    id = 3,
    name = "Intitut Teknologi Sepuluh Nopember",
    imgLogo = "https://www.its.ac.id/wp-content/uploads/2020/07/Lambang-ITS-2-300x300.png",
    imgBanner = "https://awsimages.detik.net.id/community/media/visual/2021/06/11/kampus-its-surabaya.jpeg?w=600&q=90",
    description = "Universitas Digital Indonesia berkomitmen dalam pendidikan berbasis teknologi digital dan inovasi startup. Universitas Digital Indonesia berkomitmen dalam pendidikan berbasis teknologi digital dan inovasi startup.Universitas Digital Indonesia berkomitmen dalam pendidikan berbasis teknologi digital dan inovasi startup.",
    acronym = "ITS",
    faculty = listOf(
      Faculty(
        name = "Fakultas Ilmu Komputer",
        major = listOf("Sistem Informasi", "Teknik Informatika", "Rekayasa Perangkat Lunak")
      ),
      Faculty(
        name = "Fakultas Desain dan Kreatif",
        major = listOf("Desain Komunikasi Visual", "Seni Rupa", "Multimedia")
      ),
      Faculty(
        name = "Fakultas Ilmu Komputer",
        major = listOf("Sistem Informasi", "Teknik Informatika", "Rekayasa Perangkat Lunak")
      ),
      Faculty(
        name = "Fakultas Desain dan Kreatif",
        major = listOf("Desain Komunikasi Visual", "Seni Rupa", "Multimedia")
      ),
      Faculty(
        name = "Fakultas Ilmu Komputer",
        major = listOf("Sistem Informasi", "Teknik Informatika", "Rekayasa Perangkat Lunak")
      ),
      Faculty(
        name = "Fakultas Desain dan Kreatif",
        major = listOf("Desain Komunikasi Visual", "Seni Rupa", "Multimedia")
      )
    ),
    website = "https://www.its.ac.id/"
  )
)