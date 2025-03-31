import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.googleDevtoolsKsp)
  alias(libs.plugins.daggerHilt)
  id("kotlin-parcelize")
}

fun getProperties(key: String): String? {
  val properties = Properties()
  properties.load(project.rootProject.file("local.properties").inputStream())
  return properties.getProperty(key)
}


android {
  namespace = "com.anugrah.majorsmatch"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.anugrah.majorsmatch"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    buildConfigField("String", "BASE_URL", "\"${getProperties("baseUrl")}\"")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)

  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.runtime.ktx)
  ksp(libs.androidx.room.compiler)

  implementation(libs.google.dagger.hilt.android)
  implementation(libs.androidx.hilt.navigation.compose)
  ksp(libs.google.dagger.compiler)
  ksp(libs.google.dagger.hilt.android.compiler)

  annotationProcessor(libs.androidx.room.compiler)
  annotationProcessor(libs.google.dagger.android.processor)

  implementation (libs.androidx.material.icons.extended)

  implementation (libs.androidx.lifecycle.viewmodel.compose)
  implementation (libs.androidx.navigation.compose)

  implementation(libs.coil.compose)
  implementation(libs.okhttp3)
  implementation(libs.okhttp3.logging.interceptor)
  implementation(libs.retrofit2)
  implementation(libs.retrofit2.converter.gson)
  implementation(libs.retrofit2.converter.scalars)

  implementation(libs.androidx.datastore.preferences)

  implementation (libs.accompanist.pager)
  implementation (libs.accompanist.pager.indicators)

  implementation(libs.coil.compose)
}