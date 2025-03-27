package com.anugrah.majorsmatch.data.dummy

import com.anugrah.majorsmatch.domain.model.Faculty
import com.anugrah.majorsmatch.domain.model.Major
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
        code = "FT",
        majors = listOf(
          Major(
            majorName = "Teknik Informatika",
            majorCode = "TI",
            facultyCode = "FT"
          ),
          Major(
            majorName = "Teknik Elektro",
            majorCode = "TE",
            facultyCode = "FT"
          ),
          Major(
            majorName = "Teknik Mesin",
            majorCode = "TM",
            facultyCode = "FT"
          ),
          Major(
            majorName = "Teknik Sipil",
            majorCode = "TS",
            facultyCode = "FT"
          )
        )
      ),
      Faculty(
        name = "Fakultas Ekonomi dan Bisnis",
        code = "FEB",
        majors = listOf(
          Major(
            majorName = "Manajemen",
            majorCode = "M",
            facultyCode = "FEB"
          ),
          Major(
            majorName = "Akuntansi",
            majorCode = "A",
            facultyCode = "FEB"
          ),
          Major(
            majorName = "Ekonomi Pembangunan",
            majorCode = "EP",
            facultyCode = "FEB"
          )
        )
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
        code = "FSM",
        majors = listOf(
          Major(
            majorName = "Matematika",
            majorCode = "MTK",
            facultyCode = "FSM"
          ),
          Major(
            majorName = "Fisika",
            majorCode = "FSK",
            facultyCode = "FSM"
          ),
          Major(
            majorName = "Kimia",
            majorCode = "K",
            facultyCode = "FSM"
          ),
          Major(
            majorName = "Biologi",
            majorCode = "B",
            facultyCode = "FSM"
          )
        )
      ),
      Faculty(
        name = "Fakultas Ilmu Sosial",
        code = "FIS",
        majors = listOf(
          Major(
            majorName = "Ilmu Komunikasi",
            majorCode = "IK",
            facultyCode = "FIS"
          ),
          Major(
            majorName = "Sosiologi",
            majorCode = "S",
            facultyCode = "FIS"
          ),
          Major(
            majorName = "Ilmu Politik",
            majorCode = "IP",
            facultyCode = "FIS"
          )
        )
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
        name = "Fakultas Sains dan Matematika",
        code = "FSM",
        majors = listOf(
          Major(
            majorName = "Matematika",
            majorCode = "M",
            facultyCode = "FSM"
          ),
          Major(
            majorName = "Fisika",
            majorCode = "F",
            facultyCode = "FSM"
          ),
          Major(
            majorName = "Kimia",
            majorCode = "K",
            facultyCode = "FSM"
          ),
          Major(
            majorName = "Biologi",
            majorCode = "B",
            facultyCode = "FSM"
          )
        )
      ),
      Faculty(
        name = "Fakultas Ilmu Sosial",
        code = "FIS",
        majors = listOf(
          Major(
            majorName = "Ilmu Komunikasi",
            majorCode = "IK",
            facultyCode = "FIS"
          ),
          Major(
            majorName = "Sosiologi",
            majorCode = "S",
            facultyCode = "FIS"
          ),
          Major(
            majorName = "Ilmu Politik",
            majorCode = "IP",
            facultyCode = "FIS"
          )
        )
      )
    ),
    website = "https://www.its.ac.id/"
  )
)