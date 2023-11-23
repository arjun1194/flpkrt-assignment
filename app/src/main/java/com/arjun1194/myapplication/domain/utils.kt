package com.arjun1194.myapplication.domain

import java.text.SimpleDateFormat
import java.util.Date

fun Long.toTimeString(): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date((this * 1000).toLong())
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
        ""
    }
}