package com.amirjaveed.kotlinbaseunittest.ui.addspendfragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amirjaveed.kotlinbaseunittest.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//class AddSpendFragmentTest : TestCase()

@RunWith(AndroidJUnit4::class)
class AddSpendFragmentTest {

    private lateinit var scenario: FragmentScenario<AddSpendFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.AppTheme)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testAddingSpend() {
        val amount = 0
        val desc = "Bought Eggs"
        //Espresso Matcher and Action
        Espresso.onView(withId(R.id.etAmount)).perform(ViewActions.typeText(amount.toString()))
        Espresso.onView(withId(R.id.etDescription)).perform(ViewActions.typeText(desc))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.btnAddSpend)).perform(ViewActions.click())

        //Assertion
        Espresso.onView(withId(R.id.tvResult))
            .check(ViewAssertions.matches(ViewMatchers.withText("Spend Added")))
    }
}