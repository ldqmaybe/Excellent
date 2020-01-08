package com.ldq.excellent.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * @author LinDingQiang
 * @time 2019/12/27 16:59
 * @email dingqiang.l@verifone.cn
 */
object StringUtils {
    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(
        date: String?,
        oldFormat: String?,
        newFormat: String?
    ): String? {
        return try {
            SimpleDateFormat(newFormat).format(
                SimpleDateFormat(
                    oldFormat
                ).parse(date)
            )
        } catch (var4: ParseException) {
            var4.printStackTrace()
            null
        }
    }
}