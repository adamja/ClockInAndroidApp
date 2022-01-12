package au.com.jacobdev.clockinapp.util

import android.text.TextUtils.substring
import java.util.*

fun getUuid(length: Int = -1): String {
    val uuid = UUID.randomUUID().toString().replace("-", "")
    return if (length != -1) uuid.substring(0, length) else uuid
}