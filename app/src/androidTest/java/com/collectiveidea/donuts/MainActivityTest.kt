package com.collectiveidea.donuts

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest @RunWith(AndroidJUnit4::class) class MainActivityTest {

  @Rule var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test fun mainActivityTest() {
    val textView = onView(allOf(withText("Hello World!"), childAtPosition(
        allOf(withId(R.id.activity_main), childAtPosition(withId(android.R.id.content), 0)), 0),
        isDisplayed()))
    textView.check(matches(withText("Hello World!")))
  }

  private fun childAtPosition(parentMatcher: Matcher<View>,
      position: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent) && view == parent.getChildAt(
            position)
      }
    }
  }
}
