package com.test.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import pageobjects.LoginPage

class LoginInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun shouldValidLoginPage() {
        onView(withId(R.id.textViewLogo))
            .check(matches(withText("News")))
        onView(withId(R.id.editTextUserName))
            .check(matches(isDisplayed()))
        onView(withId(R.id.editTextPassword))
            .check(matches(isDisplayed()))
        onView(withId(R.id.buttonLogin))
            .check(matches(withText("Login")))
     }

    @Test
    fun shouldntLoginWithInvalidUserName() {
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText("user4"))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(VALID_USER_PASSWORD))
        onView(withId(R.id.buttonLogin))
            .perform(click())
        onView(withId(R.id.editTextUserName)).check(matches(hasErrorText("Wrong user name")))
    }

    @Test
    fun shouldntLoginWithInvalidPassword() {
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText(VALID_USER_NAME))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(INVALID_USER_PASSWORD))
        onView(withId(R.id.buttonLogin))
            .perform(click())
        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText("Wrong password")))
    }

    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val VALID_USER_PASSWORD = "password"
        private const val INVALID_USER_NAME = "userr2"
        private const val INVALID_USER_PASSWORD = "passwordd"
    }
}
