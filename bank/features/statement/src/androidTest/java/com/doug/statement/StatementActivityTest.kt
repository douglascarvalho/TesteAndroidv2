package com.doug.statement

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.douglas.actions.extras.Account
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import okhttp3.mockwebserver.MockResponse

@RunWith(AndroidJUnit4::class)
class StatementActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(StatementActivity::class.java, true, false)

    @get:Rule
    val coroutinesRule = instantLiveDataAndCoroutineRules

    @get:Rule
    var okHttpRule = OkHttpIdlingResourceRule()

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        startKoin {}
    }

    @Test
    fun giveAccount_assertItemsAreOnScreen() {
        val intent = Intent()
        intent.putExtra(Account.EXTRA_KEY, Account(1, "doug", "123456", "4444", 44.toDouble()))

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("{}"))

        activityTestRule.launchActivity(intent)

        onView(withId(R.id.account_label)).check(matches(isDisplayed()))
        onView(withId(R.id.balance_label)).check(matches(isDisplayed()))
        onView(withId(R.id.account_owner)).check(matches(withText("doug")))
        onView(withId(R.id.account_number)).check(matches(withText("4444 / 12.345-6")))
    }
}