package com.ming.logisticsmanagement.extensions

fun String.isValidUserName():Boolean = this.matches(Regex("^[0-9]{8}$"))
fun String.isValidPassword():Boolean = this.matches(Regex("^[a-zA-Z]\\w{3,15}$"))
fun String.isValidPhoneNumber():Boolean = this.matches(Regex("^1[3-9][0-9]{9}$"))
fun String.isValidnumberOfPackages():Boolean = this.matches(Regex("^[1-9][0-9]{0,2}$"))
fun String.isValidfreightPaid():Boolean = this.matches(Regex("^[1-9][0-9]{0,3}$"))