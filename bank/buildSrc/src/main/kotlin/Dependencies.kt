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
    val androidx_recyclerview = "1.1.0-alpha04"
    val androidx_constraintLayout = "1.1.3"
    val androidx_fragment = "1.1.0-alpha07"
    val androidx_room = "2.1.0-alpha07"

    val koin = "2.0.0-rc-2"

    val material = "1.1.0-alpha05"

    val retrofit = "2.5.0"
    val retrofit_coroutines_adapter = "0.9.2"

    val gson = "2.8.5"

    val junit = "4.12"
    val androidx_espresso = "3.1.0"
    val androidx_testing = "1.1.1"
    val mockwebserver = "3.12.0"

    val gradleandroid = "3.4.0"
    val kotlin = "1.3.21"
    val kotlin_coroutines = "1.2.0"
    val kotlin_coroutines_test = "1.1.1"

    val arch_core_testing = "1.1.1"

    val espresso_idling_resource = "3.2.0-alpha05"
    val okhttp_idling_resource = "1.0.0"

    val mockito_inline = "2.7.13"
    val mockitokotlint2 = "2.1.0"
    val mockk_android = "1.9.3"
    val objenesis = "2.6"

    val logging_interceptor = "3.11.0"

    val saferoomx = "1.0.2"
    val multidex = "1.0.3"
}

object Dependencies {
    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"
    val androidx_fragment = "androidx.fragment:fragment:${Versions.androidx_fragment}"
    val androidx_room_runtime = "androidx.room:room-runtime:${Versions.androidx_room}"
    val androidx_room_compiler = "androidx.room:room-compiler:${Versions.androidx_room}"

    val koin_android = "org.koin:koin-android:${Versions.koin}"
    val koin_androidx_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    val androidx_material = "com.google.android.material:material:${Versions.material}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofit_coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit_coroutines_adapter}"

    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val testlib_junit = "junit:junit:${Versions.junit}"

    val testandroidx_rules = "androidx.test:rules:${Versions.androidx_testing}"
    val testandroidx_runner = "androidx.test:runner:${Versions.androidx_testing}"
    val testandroidx_espressocore = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"

    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"

    val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
    val kotlin_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines_test}"

    val arch_core_testing = "android.arch.core:core-testing:${Versions.arch_core_testing}"

    val espresso_idling_resource = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso_idling_resource}"
    val okhttp_idling_resource = "com.jakewharton.espresso:okhttp3-idling-resource:${Versions.okhttp_idling_resource}"

    val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_inline}"
    val mockitokotlint2 = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitokotlint2}"
    val mockk_android = "io.mockk:mockk-android:${Versions.mockk_android}"
    val objenesis = "org.objenesis:objenesis:${Versions.objenesis}"

    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"

    val saferoomx = "com.commonsware.cwac:saferoom.x:${Versions.saferoomx}"
    val multidex = "com.android.support:multidex:${Versions.multidex}"
    val allopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"

}