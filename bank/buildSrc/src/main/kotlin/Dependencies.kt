import org.gradle.api.JavaVersion

object Config {
    val minSdk = 19
    val compileSdk = 28
    val targetSdk = 28
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "28.0.3"
}

object Versions {
    val androidx_core = "1.0.1"
    val androidx_appcompat = "1.1.0-alpha04"
    val androidx_recyclerview = "1.0.0"
    val androidx_constraintLayout = "1.1.3"

    val koin = "2.0.0-rc-2"

    val material = "1.1.0-alpha05"

    val retrofit = "2.5.0"

    val junit = "4.12"
    val androidx_espresso = "3.1.0"
    val androidx_testing = "1.1.1"

    val gradleandroid = "3.4.0"
    val kotlin = "1.3.21"
}

object Dependencies {
    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"

    val koin_android = "org.koin:koin-android:${Versions.koin}"
    val koin_androidx_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    val androidx_material = "com.google.android.material:material:${Versions.material}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    val testlib_junit = "junit:junit:${Versions.junit}"

    val testandroidx_rules = "androidx.test:rules:${Versions.androidx_testing}"
    val testandroidx_runner = "androidx.test:runner:${Versions.androidx_testing}"
    val testandroidx_espressocore = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"

    val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}