package com.collectiveidea.donuts

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest @RunWith(AndroidJUnit4::class) class MainActivityTest {

  @Suppress("unused")
  @get:Rule var activityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test fun defaultAvailabilityMessage_displaysByDefault() {
    val availabilityTextView = onView(withId(R.id.donuts_availability))
    availabilityTextView
        .check(matches(withText("No Donuts Today :(")))
        .check(matches(isDisplayed()))
  }
}
