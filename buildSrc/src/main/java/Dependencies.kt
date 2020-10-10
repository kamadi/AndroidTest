import Kotlin.coroutinesVersion

object Kotlin {
    const val coroutinesVersion = "1.3.5"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
}

object ArchitectureComponents {
    private const val version = "2.1.0"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$version"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-common-java8:$version"
}

object Network {
    private const val retrofitVersion = "2.8.1"
    private const val okHttpVersion = "4.8.0"
    private const val gsonVersion = "2.8.5"

    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val okHttpTls = "com.squareup.okhttp3:okhttp-tls:$okHttpVersion"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    const val retrofitXml = "com.squareup.retrofit2:converter-simplexml:2.5.0"
    const val gson = "com.google.code.gson:gson:$gsonVersion"
}

object Dagger {
    private const val daggerVersion = "2.+"
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val android = "com.google.dagger:dagger-android:$daggerVersion"
    const val androidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    const val androidProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
}

object UI {
    const val picasso = "com.squareup.picasso:picasso:2.71828"
}

object Android {
    const val appCompat = "androidx.appcompat:appcompat:1.2.0"
    const val material = "com.google.android.material:material:1.3.0-alpha01"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
    const val ktx = "androidx.core:core-ktx:1.3.2"
}