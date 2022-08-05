package com.amirjaveed.kotlinbaseunittest.data.models

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest : TestCase(){

    @Test
    fun whenValidInput(){
        val amount=0
        val description="Description"
        val result=Validator.validateInput(amount,description)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInvalidInput(){
        val amount=10
        val description=""
        val result=Validator.validateInput(amount,description)
        assertThat(result).isEqualTo(false)
    }
}