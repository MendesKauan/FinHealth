package com.example.finhealth.ui.theme.utils

import java.security.SecureRandom

class generatorID  {
    private val random = SecureRandom()
    private val length = 16

     fun generate(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val randomString = StringBuilder()

        repeat(length) {
            val randomIndex = this.random.nextInt(chars.length)
            randomString.append(chars[randomIndex])
        }

         return randomString.toString()

    }

}