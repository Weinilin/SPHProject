package com.example.sphproject

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import android.content.Intent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.sphproject.Models.RecordsResponse
import org.hamcrest.Matchers.not


@RunWith(AndroidJUnit4::class)
class MainActivityUnitTest {
    val data: ArrayList<RecordsResponse> = arrayListOf()
    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        data.add(RecordsResponse("1.2", "2009-1", 1))
        data.add(RecordsResponse("12.0", "2009-1", 1))
        data.add(RecordsResponse("102.0", "2109-1", 1))

    }

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





