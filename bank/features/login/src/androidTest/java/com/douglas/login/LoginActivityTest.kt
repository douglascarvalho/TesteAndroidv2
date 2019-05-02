package com.douglas.login

import android.content.Context
import android.content.Intent
import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.douglas.login.data.LoginApi
import com.douglas.login.data.source.LoginDataSource
import com.douglas.login.data.source.LoginRepository
import com.douglas.login.data.source.local.LoginDatabase
import com.douglas.login.data.source.local.LoginLocalDataSource
import com.douglas.login.data.source.remote.LoginRemoteDataSource
import com.douglas.login.injection.initializeLoginModule
import com.douglas.login.usecase.LoginUseCase
import com.douglas.network.RetrofitClient
import io.mockk.every
import io.mockk.mockkStatic
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(LoginActivity::class.java, true, false)

    private lateinit var server: MockWebServer


    @Before
    fun setup() {
        val context = mock(Context::class.java)

        server = MockWebServer()
        server.start()

        startKoin {}

        val loginTestModule = module{
            single {
                Room.inMemoryDatabaseBuilder(context, LoginDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
            }

            single { get<LoginDatabase>().loginDao() }

            single { RetrofitClient.retrofit().create<LoginApi>(LoginApi::class.java) }

            single<LoginDataSource>(named("local")) { LoginLocalDataSource(get()) }
            single<LoginDataSource>(named("remote")) { LoginRemoteDataSource(get()) }
            single { LoginRepository(get(named("local")), get(named("remote"))) }

            factory { LoginUseCase(get()) }
            factory { LoginMapper() }

            viewModel { LoginViewModel(get(), get()) }
        }

        mockkStatic("com.douglas.login.injection.LoginModuleKt")
        every { initializeLoginModule() } returns loadKoinModules(loginTestModule)
    }

    @Test
    fun giveLogin_executeLoginTest() {
        val intent = Intent()

        activityTestRule.launchActivity(intent)

        onView(withId(R.id.login)).perform(click())
    }

}