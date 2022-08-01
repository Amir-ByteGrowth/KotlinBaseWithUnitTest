package com.amirjaveed.kotlinbaseunittest.data.models

object Validator {
    fun validateInput(amount: Int, desc: String): Boolean {
        return (amount > 0 && desc.isNotEmpty())
    }
}