package domain.extension

fun Int?.orZero() : Int = this ?: 0

fun String?.orEmpty() : String = this ?: ""

fun Boolean?.orFalse() : Boolean = this ?: false