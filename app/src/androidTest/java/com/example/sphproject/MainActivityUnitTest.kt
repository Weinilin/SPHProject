package com.example.sphproject

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import android.content.Intent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed


@RunWith(AndroidJUnit4::class)
class MainActivityUnitTest { private val baseUrl = "https://data.gov.sg/api/action/"

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testDisplayOfUiText() {
        // Assign
        mActivityRule.launchActivity(Intent())

        /* check if the ViewHolder is being displayed */
        onView(
            RecyclerViewMatcher(R.id.list)
                .atPositionOnView(0, R.id.txtYearData)
        ).check(matches(isDisplayed()));

        /* checking for the text of the first one item */
        onView(
            RecyclerViewMatcher(R.id.list)
                .atPositionOnView(1, R.id.txtYearData)
        )
            .check(
                matches(
                    (withText("2005 \n 0.002773"))
                )
            )
    }

    @Test
    fun testDisplayOfImage() {
        // Assign
        mActivityRule.launchActivity(Intent())

        // Act
        /* check if the ViewHolder is being displayed */
        onView(
            RecyclerViewMatcher(R.id.list)
                .atPositionOnView(9, R.id.imageview)
        ).check(matches(isDisplayed()))

        /* checking for the text of the first one item */
        onView(
            RecyclerViewMatcher(R.id.list)
                .atPositionOnView(9, R.id.imageview)
        )
            .check(
                matches(
                    (withEffectiveVisibility(Visibility.VISIBLE))
                )
            )
    }

}





