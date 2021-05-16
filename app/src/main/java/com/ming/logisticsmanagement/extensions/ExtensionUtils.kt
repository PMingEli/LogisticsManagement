package com.ming.logisticsmanagement.extensions

fun String.isValidUserName():Boolean = this.matches(Regex("^[0-9]{8}$"))
fun String.isValidPassword():Boolean = this.matches(Regex("^[a-zA-Z]\\w{3,15}$"))